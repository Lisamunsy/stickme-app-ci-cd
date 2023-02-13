import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import LabelValues from './components/commons/LabelValues.vue';
// Import de nos plugins :
import toast from './plugins/toast';
import axios from './plugins/axios';
import dayjs from 'dayjs';
// Importer les styles en JS pour que Vite "inline" les styles
// dans index.html (cf. avec outils dev dans le navigateur)
import './assets/styles.css';

// Instanciation de l'application Vue a partir du "root component" App.vue :
const app = createApp(App);
// Enregistrement du router comme un plugin :
app.use(router);
// Composant global, pas utile de l'importer ou on en a besoin :
app.component('LabelValues', LabelValues);
// Enregistrement des plugins :
app.use(toast);
app.use(axios);
// Gestionnaire global des erreurs qui ne sont pas gerees ailleurs (selon les besoins) :
app.config.errorHandler = (err, comp) => {
    console.error('Vue Global ErrorHandler', err);
    comp.$toast.error('toast-global', 'A mystic error occurred, sorry for the inconvenience.');
};
// Day JS, enregistrement d'une propriete globale pour faciliter l'utilisation de Day JS
app.config.globalProperties.$fmt = {
    date: (str) => {
        if (str === null) {
            return 'N/A';
        }
        const date = dayjs(str);
        return date.format('YYYY/MM/DD');
    }
};

// On "monte" l'application dans l'element racine (lien avec le DOM) :
app.mount('#app');
