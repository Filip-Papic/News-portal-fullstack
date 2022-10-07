import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'

import { createPinia, PiniaVuePlugin } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

import VueCompositionAPI from '@vue/composition-api'

Vue.use(PiniaVuePlugin)
Vue.use(VueCompositionAPI)

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.config.productionTip = false

Vue.filter('capitalize', function(value) {
  if (!value) {
    return '';
  }

  value = value.toString();

  return value.charAt(0).toUpperCase() + value.slice(1)
})

new Vue({
  router,
  pinia,
  render: h => h(App)
}).$mount('#app')

