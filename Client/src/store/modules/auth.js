import { defineStore } from 'pinia'
import Jwt from '@/common/jwt'
import { login } from '@/api'
import router from '@/router'

export const useAuthStore = defineStore({
	id: 'auth',
	state: () => ({
		user: JSON.parse(localStorage.getItem('user')) === null ? { id: 0, name: 'Anonymous', usertype: 'Anonymous' } : JSON.parse(localStorage.getItem('user')),
	}),
	actions: {
		login(email, password) {
            login(email, password).then((response) => {
                if(response.status === 200) {
                    const user = JSON.parse(atob(response.data.jwt.split('.')[1]))

                    if(user.status !== 'active') {
                        alert('User is not active')
                        return
                    }

                    this.user = user
                    
                    Jwt.setToken(response.data.jwt)
                    localStorage.setItem('user', JSON.stringify(user))

                    router.push({name: 'Home'});
                    router.go();
                }
            }).catch((error) => {
                alert('Invalid credentials')
                console.log(error)
            })
		},
        logout() {
            localStorage.removeItem('jwt')
            localStorage.removeItem('user')
            localStorage.removeItem('news')
            localStorage.removeItem('comments')
            this.user = { id: 0, name: 'Anonymous', usertype: 'Anonymous' }
            router.push({ name: 'Login' })
        }
	}
})