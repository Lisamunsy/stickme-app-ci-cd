<script>
export default {
    data() {
        return {
            count: 0
        }
    },
    methods: {
        getElementFromDOM() {
            return document.getElementById('element');
        },
        isAvailableInDOM() {
            return this.getElementFromDOM() !== null;
        }, getDOMCountValue() {
            return document.getElementById('count').textContent;
        }
    },
    beforeCreate() {
        console.log('Executing beforeCreate hook...');
        // Uncomment line to see that methods are not yet available before created! (error raised)
        // console.log(`Element available in DOM=${this.isAvailableInDOM()}`);
        console.log('Methods not yet available, component is not yet created!');
        console.log(`Data count=${this.count}`);
        console.log('Data not yet initialized, component is not yet created!');
        console.log('beforeCreate hook executed');
        console.log('#################');
    },
    created() {
        console.log('Executing created hook...');
        console.log(`Element available in DOM=${this.isAvailableInDOM()}`);
        console.log(`Data count=${this.count}`);
        console.log('created hook executed');
        console.log('#################');
    },
    beforeMount() {
        console.log('Executing beforeMount hook...');
        console.log(`Element available in DOM=${this.isAvailableInDOM()}`);
        console.log(`Data count=${this.count}`);
        console.log('beforeMount hook executed');
        console.log('#################');
    },
    mounted() {
        console.log('Executing mounted hook...');
        console.log(`Element available in DOM=${this.isAvailableInDOM()}`);
        console.log(`Data count=${this.count}`);
        console.log('mounted hook executed');
        console.log('#################');
    },
    beforeUpdate() {
        console.log('Executing beforeUpdate hook...');
        console.log(`Element available in DOM=${this.isAvailableInDOM()}`);
        console.log(`DOM count=${this.getDOMCountValue()}`);
        console.log(`Data count=${this.count}`);
        console.log('beforeUpdate hook executed');
        console.log('#################');
    },
    updated() {
        console.log('Executing updated hook...');
        console.log(`Element available in DOM=${this.isAvailableInDOM()}`);
        console.log(`DOM count=${this.getDOMCountValue()}`);
        console.log(`Data count=${this.count}`);
        console.log('updated hook executed');
        console.log('#################');
    }
}
</script>
<template>
    <h1>Vue 3 component <a href="https://vuejs.org/guide/essentials/lifecycle.html" target="_blank">life cycle</a> and
        data binding</h1>
    <p id="element">Have a look at the JS console Dude...</p>
    <hr>
    <button type="button" @click="count++">Click to update some data (count)...</button>&nbsp;
    <p>
        <strong>DOM count=</strong><span id="count">{{ count }}</span>
    </p>
    <hr>
    <p>Set data count value as you like: <input type="number" v-model="count"></p>
</template>
