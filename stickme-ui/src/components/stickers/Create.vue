<script>
import { useVuelidate } from '@vuelidate/core';
import { required, maxLength, minValue, between } from '@vuelidate/validators';

export default {
    setup() {
        return {
            validator: useVuelidate({ $autoDirty: true })
        }
    },
    data() {
        return {
            baseUrl: import.meta.env.VITE_IMG_BASE_URL,
            sizes: [],
            aspects: [],
            inputs: {
                name: null,
                description: null,
                imageUrl: null,
                sizeId: 0,
                aspectId: 0,
                price: null
            }
        }
    },
    validations() {
        return {
            inputs: {
                name: { required, maxLength: maxLength(100) },
                description: { required, maxLength: maxLength(1000) },
                imageUrl: { required, maxLength: maxLength(50) },
                sizeId: { minValue: minValue(1) },
                aspectId: { minValue: minValue(1) },
                price: { required, between: between(0.01, 100.0) }
            }
        }
    },
    methods: {
        async submit() {
            const resp = await this.$http.post('/stickers', this.inputs);
            if (resp.status === 204) {
                Object.assign(this.inputs, this.$options.data().inputs);
                this.validator.$reset();
                this.$toast.success('toast-global', 'Sticker created with success.');
            } else {
                console.error(resp);
                this.$toast.error('toast-global', 'Server conversion or validation error.');
            }
        },
        async initSizes() {
            // this = l'instance du componsant
            // $http = l'instance d'axios telle que declarer dans les 
            // proprietes globales en s'appuyant sur un plugin pour l'enregsitrer dans l'application
            const resp = await this.$http.get('/sizes');
            // resp = reponse "simplifiee" grace a l'interceptor (cf. plugin axios)
            this.sizes = resp.body;
        },
        async initAspects() {
            const resp = await this.$http.get('/aspects');
            this.aspects = resp.body;
        }
    },
    beforeMount() {
        this.initSizes();
        this.initAspects();
    }
}
</script>

<template>
    <h1>Create a new sticker</h1>
    <form novalidate @submit.prevent="submit">
        <div class="row mb-3">
            <div class="col-12">
                <label for="name" class="form-label required">Name</label>
                <input v-model.trim="inputs.name" id="name" name="name" type="text" maxlength="100" class="form-control"
                    :class="{ 'is-invalid': validator.inputs.name.$error }">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-12">
                <label for="description" class="form-label required">Description</label>
                <textarea v-model.trim="inputs.description" id="description" name="description" maxlength="1000"
                    rows="3" class="form-control"
                    :class="{ 'is-invalid': validator.inputs.description.$error }"></textarea>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-12">
                <label for="imageUrl" class="form-label required">Image URL</label>
                <div class="input-group">
                    <span class="input-group-text">{{ baseUrl }}</span>
                    <input v-model.trim="inputs.imageUrl" id="imageUrl" name="imageUrl" type="text" maxlength="50"
                        class="form-control" :class="{ 'is-invalid': validator.inputs.imageUrl.$error }">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="sizeId" class="form-label required">Size</label>
                <select v-model.number="inputs.sizeId" id="sizeId" name="sizeId" class="form-select"
                    :class="{ 'is-invalid': validator.inputs.sizeId.$error }">
                    <option selected disabled value="0">Choose a size</option>
                    <LabelValues :items="sizes" />
                </select>
            </div>
            <div class="col-md-4 mb-3">
                <label for="aspectId" class="form-label required">Aspect</label>
                <select v-model.number="inputs.aspectId" id="aspectId" name="aspectId" class="form-select"
                    :class="{ 'is-invalid': validator.inputs.aspectId.$error }">
                    <option selected disabled value="0">Choose an aspect</option>
                    <LabelValues :items="aspects" />
                </select>
            </div>
            <div class="col-md-4 mb-3">
                <label for="price" class="form-label required">Price</label>
                <div class="input-group">
                    <span class="input-group-text">€</span>
                    <input v-model.number="inputs.price" id="price" name="price" type="number" class="form-control"
                        :class="{ 'is-invalid': validator.inputs.price.$error }">
                </div>
            </div>
        </div>
        <div class="d-grid d-md-flex justify-content-md-end mb-3">
            <button type="submit" class="btn btn-dark" :disabled="validator.$invalid">Sauvegarder</button>
        </div>
    </form>
</template>