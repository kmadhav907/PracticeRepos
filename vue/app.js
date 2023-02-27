console.log("Starting");

const vueApp = Vue.createApp({
    data() {
        return {
            title: "Final Empire"
        }
    }
});

vueApp.mount('#app');