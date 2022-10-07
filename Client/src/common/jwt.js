const token = 'jwt'

export const getToken = () => {
    return window.localStorage.getItem(token)
}

export const setToken = (value) => {
    return window.localStorage.setItem(token, value)
}

export const removeToken = () => {
    return window.localStorage.removeItem(token)
}

export default {
    getToken, setToken, removeToken
};