import { createRouter, createWebHistory } from 'vue-router';

/*
 * Le router declare les composants en "lazy loading".
 * Ils ne sont pas importes avant que le composant ne soit utile a une route.
 * Le "lazy loading" revient a declarer une fonction qui importe le composant 
 * au lieu de referencer en "dur" le nom du composant importe en haut du script.
*/

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../components/stickers/Home.vue')
    }, {
      path: '/admin/stickers',
      name: 'stickers-edit',
      component: () => import('../components/stickers/Edit.vue')
    }, {
      path: '/admin/stickers/create',
      name: 'sticker-create',
      component: () => import('../components/stickers/Create.vue')
    }, {
      path: '/stickers/:id/detail',
      name: 'sticker-detail',
      component: () => import('../components/stickers/Detail.vue')
    }, {
      path: '/admin/stickers/:id/update',
      name: 'sticker-update',
      component: () => import('../components/stickers/Update.vue')
    }, {
      path: '/life-cycle',
      name: 'life-cycle',
      component: () => import('../components/LifeCycle.vue')
    }
  ]
})

export default router;
