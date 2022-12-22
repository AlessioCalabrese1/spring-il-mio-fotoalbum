<template>
    <div>

        <nav class="navbar navbar-expand-lg bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="http://localhost:8081">Foto Album</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="http://localhost:8080/">Admin Area</a>
                        </li>
                    </ul>
                    <form class="d-flex" role="search" @submit.prevent="searchByTitleOrTag(query)">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
                            name="query" v-model="query">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="container mt-5">
            <div class="row justify-content-between">
                <div class="col-4 d-flex justify-content-center card-group" v-for="pic in pictures" :key="pic.id"
                    :class="pic.valid == true ? '' : 'd-none'">
                    <div class="card mb-5" style="width: 30rem;" v-if="pic.valid">
                        <div class="card-body p-0">
                            <div style="height: 275px;">
                                <img :src="pic.imgURL" class="img-fluid rounded-top" style="height: 275px;">
                            </div>
                            <h5 class="card-title mt-3">{{ pic.title }}</h5>
                            <p class="card-text p-x3">{{ pic.description }}</p>

                            <div class="categories m-3">
                                <span v-for="cat in pic.categories" :key="cat.id" class="m-2 badge text-bg-info">
                                    {{ cat.name }}
                                </span>
                            </div>

                            <div class="tags m-3">
                                <span v-for="tag in pic.tags" :key="tag.id" class="m-2 badge text-bg-dark">
                                    {{ tag.name }}
                                </span>
                            </div>
                        </div>

                        <div class="card-footer">
                            <div @click="viewComments(pic.id)" class="view">View Comments</div>
                            <div v-if="indexViewComments == pic.id" class="mt-3">
                                <form class="d-flex pb-3" role="search"
                                    @submit.prevent="saveNewComment(pic.id, newComment)">
                                    <textarea class="form-control me-2" name="text" cols="30" rows="3"
                                        v-model="newComment.text" placeholder="Add Comment"></textarea>
                                    <button class="btn btn-outline-success" type="submit">Add Comment</button>
                                </form>

                                <div v-for="comment in pic.comments" :key="comment.id" class="mt-1 pb-3 border-bottom">
                                    {{ comment.text }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div v-if="pictures.length <= 0">
                    <h3>There are no photos!</h3>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

const URL_LOGIN = "http://localhost:8080/"
const API_URL = "http://localhost:8080/api/1/picture";
const API_URL_COMMENTS = "http://localhost:8080/api/1/comment";
const INDEX_VIEW_COMMENTS = -1;

export default {
    name: "HomeComponent",

    data() {
        return {
            pictures: [],
            newComment: {},
            query: "",
            indexViewComments: INDEX_VIEW_COMMENTS,
            sortedPictures: []
        }
    },

    methods: {
        getPicIndexById(id) {
            for (let index = 0; index < this.pictures.length; index++) {
                const pic = this.pictures[index]
                if (id == pic.id) {
                    return index
                }
            }
        },

        getAllPictures() {
            axios.get(API_URL)
                .then(response => {
                    console.log(response.data)
                    this.pictures = response.data
                }).catch(err => {
                    console.error(err)
                })
        },

        saveNewComment(id, newComment) {
            axios.post(API_URL_COMMENTS + "/create/" + id, newComment)
                .then(resp => {
                    this.pictures[this.getPicIndexById(id)].comments.push(resp.data)
                }).catch(err => {
                    console.error(err)
                })
        },

        searchByTitleOrTag(query) {
            axios.get(API_URL + "/search/" + query)
                .then(resp => {
                    this.pictures = resp.data
                }).catch(err => {
                    console.error(err);
                })
        },

        viewComments(id) {
            this.newComment = {}
            if (this.indexViewComments == id) {
                this.indexViewComments = -1;
            } else {
                this.indexViewComments = id;
            }
        }
    },

    mounted() {
        this.getAllPictures();
    }
}
</script>

<style lang="scss" scoped>
.view {
    cursor: pointer;
    color: rgb(0, 102, 255);
}
</style>