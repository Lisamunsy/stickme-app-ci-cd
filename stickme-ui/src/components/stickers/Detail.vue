<script>
import { useRoute } from 'vue-router';

export default {
    setup() {
        return {
            route: useRoute()
        }
    },
    data() {
        return {
            id: this.route.params.id,
            baseUrl: import.meta.env.VITE_IMG_BASE_URL,
            sticker: {}
        }
    },
    methods: {
        async initSticker() {
            const resp = await this.$http.get(`/stickers/${this.id}/detail`);
            this.sticker = resp.body;
        }
    },
    beforeMount() {
        this.initSticker();
    }
}
</script>
<template>
    <h1>Sticker detail</h1>
    <div class="card mb-3 shadow-sm">
        <div class="row p-3 align-items-center">
            <div class="col-md-4 text-center">
                <img :src="baseUrl + sticker.imageUrl" class="img-fluid" :alt="sticker.name">
            </div>
            <div class="col-md-8">
                <div class="card-body p-0">
                    <h2>{{ sticker.name }}</h2>
                    <p class="card-text">{{ sticker.description }}</p>
                    <p class="card-text fs-5">
                        <span class="badge bg-primary me-2">{{ sticker.sizeName }}</span>
                        <span class="badge bg-primary">{{ sticker.aspectName }}</span>
                    </p>
                    <p class="card-text d-flex align-items-center">
                        <span class="position-relative m-0 fs-2">€&nbsp;{{ sticker.price }}</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>