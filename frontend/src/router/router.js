import StartPage from "@/pages/StartPage";
import {createRouter, createWebHistory} from "vue-router";
import MainPage from "@/pages/MainPage";

const routes = [
    {
        path: '/',
        name: 'start-page',
        component: StartPage
    },
    {
        path: '/main',
        name: 'main-page',
        component: MainPage,
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem("jwt")) next();
            else next({
                name: 'start-page',
            });
        }
    },
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router