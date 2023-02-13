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
            const resp = await this.$http.get('/stickers/for-edit');
            this.stickers = resp.body;
        },
        async remove(id) {
            const resp = await this.$http.delete(`/stickers/${id}`);
            if (resp.status === 204) {
                this.$toast.success('toast-global', 'Sticker deleted with success.');
                await this.initStickers();
            } else {
                console.error(resp);
                this.$toast.error('toast-global', 'Server error.');
            }
        }
    },
    beforeMount() {
        this.initStickers();
    }
}
</script>
<template>
    <h1>Edit stickers</h1>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
                <tr class="text-center align-middle">
                    <th scope="col">Image</th>
                    <th class="w-100" scope="col">Name</th>
                    <th scope="col">Date added</th>
                    <th scope="col">Update</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <tbody class="text-nowrap  align-middle">
                <tr v-for="sticker in stickers">
                    <td class="text-center"><img :src="baseUrl + sticker.imageUrl" class="img-fluid"
                            :alt="sticker.name">
                    </td>
                    <td>{{ sticker.name }}</td>
                    <td>{{ this.$fmt.date(sticker.dateAdded) }}</td>
                    <td class="text-center">
                        <RouterLink :to="{ name: 'sticker-update', params: { id: sticker.id } }" title="Update sticker">
                            <i class="bi bi-pencil-square text-primary"></i>
                        </RouterLink>
                    </td>
                    <td class="text-center"><a href="#" @click="remove(sticker.id)"><i
                                class="bi bi-trash3 text-danger"></i></a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>