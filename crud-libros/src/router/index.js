import Vue from "vue";
import VueRouter from "vue-router";
import viewBooks from "../views/viewBooks.vue";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: viewBooks,
    },
  ],
});

export default router;
