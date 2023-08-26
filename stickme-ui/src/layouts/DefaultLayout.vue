<script setup>
import { RouterView } from 'vue-router';
import Header from '../components/commons/Header.vue';
import Toast from '../components/commons/Toast.vue';
</script>

<template>
    <Header />
    <main class="container-xl">
        <RouterView />
    </main>
    <div class="container-xl">
        <p><strong>Ce site Web est un site exemple dans un cadre scolaire, toutes les données sont fictives. Les
                noms, marques et
                logos utilisés appartiennent à leurs propriétaires respectifs.</strong></p>
    </div>
    <Toast id="toast-global" />
</template>
