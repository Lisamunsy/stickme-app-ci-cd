
import axios from 'axios';

/*
 * Ce plugin sert a construire une seule et meme instance d'axios pour l'application.
 * Selon les besoins on peut configurer l'instance en modifiant la configuration par defaut.
 * Par exemple, axios considere qu'une reponse du serveur est valide si le statut est < 500
 * https://axios-http.com/docs/handling_errors
 * Ici on change en precisant les statuts que l'on considere comme valide.
 * 
 * On declare egalement un interceptor basique sur les reponses, il est possible de declarer des interceptors
 * sur les requetes egalement.
 * L'interceptor ici declare une fonction qui sera appele a chaque reception d'une reponse avant que les methodes
 * get/post... ne retournent la reponse au code client.
 * https://axios-http.com/docs/interceptors
 * Dans cet exemple on simplifie l'objet reponse par defaut pour le rendre plus lisible et ne retenir que les informations
 * qui nous interesse (status et data).
 * 
 * L'instance d'axios est ensuite ajoutee dans les proprietes globales de Vue.
*/

const ACCEPTED_STATUS = [200, 201, 202, 204, 400];

export default {
    install: (app) => {
        const http = axios.create({
            // Recuperation URL de base de l'API depuis le fichier des variables d'environnement
            baseURL: import.meta.env.VITE_API_BASE_URL,
            validateStatus: (status) => {
                // Fonction de callback appelee par axios pour valider ou non une reponse
                return ACCEPTED_STATUS.includes(status);
            }
        });
        http.interceptors.response.use((response) => {
            const status = response.status;
            const data = response.data;
            // Remplacement de chaine vide par null si pas de body
            const body = data != '' ? data : null;
            return { status: status, body: body };
        }, (error) => {
            return Promise.reject(error);
        });
        app.config.globalProperties.$http = http;
    }
};
