<script>
import { RouterLink } from 'vue-router';

export default {
    data() {
        return {
            baseUrl: import.meta.env.VITE_IMG_BASE_URL,
            stickers: []
        }
    },
    methods: {
        async initStickers() {
            const resp = await this.$http.get('/stickers');
            this.stickers = resp.body;
        }
    },
    beforeMount() {
        this.initStickers();
    }
}
</script>
<template>
    <h1>Our stickers</h1>
    <div class="row row-cols-1 row-cols-md-4 g-3 mb-3">
        <div class="col" v-for="sticker in stickers">
            <div class="card shadow-sm h-100">
                <div class="p-3"><img :src="baseUrl + sticker.imageUrl" class="card-img-top" :alt="sticker.name"></div>
                <div class="card-body">
                    <h5 class="card-title">{{ sticker.name }}</h5>
                    <div class="d-flex align-items-center">
                        <span class="m-0 fs-2">€&nbsp;{{ sticker.price }}</span>
                        <RouterLink :to="{ name: 'sticker-detail', params: { id: sticker.id } }" class="ms-auto"
                            title="Details...">
                            <i class="bi bi-three-dots fs-5 text-primary"></i>
                        </RouterLink>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>