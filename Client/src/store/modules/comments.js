import { defineStore } from 'pinia'
import * as api from '@/api'

export const useCommentsStore = defineStore({
    id: 'comments',
    state: () => ({
        comments: [],
        likedComments: [],
        dislikedComments: []
    }),
    persist: {
        storage: localStorage,
        paths: ['likedComments', 'dislikedComments']
    },
    actions: {
        getCommentsByNewsId(id) {
            api.getCommentsByNewsId(id)
            .then(response => {
                this.comments = response.data;
            })
            .catch(error => {
                console.log(error);
            })
        },
        addComment(comment) {
            api.addComment(comment)
            .then(response => {
                this.comments.push(response.data);
            }).catch(error => {
                console.log(error);
            })
        },  
        updateCommentLikes(id) {
            if (!this.likedComments.includes(id) && !this.dislikedComments.includes(id)) {
                api.likeComment(id)
                .then(response => {
                    this.comments.forEach(comment => {
                        if (comment.id === id) {
                            comment.likes = response.data.likes;
                            comment.dislikes = response.data.dislikes;
                        }
                    })
                    this.likedComments.push(id);
                })
                .catch(error => {
                    console.log(error);
                })
            } else if (!this.likedComments.includes(id) && this.dislikedComments.includes(id)) {
                api.likeAfterDislikeComment(id)
                .then(response => {
                    this.comments.forEach(comment => {
                        if (comment.id === id) {
                            comment.likes = response.data.likes;
                            comment.dislikes = response.data.dislikes;
                        }
                    })
                    this.likedComments.push(id);
                    this.dislikedComments.splice(this.dislikedComments.indexOf(id), 1);
                })
                .catch(error => {
                    console.log(error);
                })
            } else if (this.likedComments.includes(id) && !this.dislikedComments.includes(id)) {
                api.unlikeComment(id)
                .then(response => {
                    this.comments.forEach(comment => {
                        if (comment.id === id) {
                            comment.likes = response.data.likes;
                            comment.dislikes = response.data.dislikes;
                        }
                    })
                    this.likedComments.splice(this.likedComments.indexOf(id), 1);
                })
                .catch(error => {
                    console.log(error);
                })
            } else {
                console.log('Error');
            }
        },
        updateCommentDislikes(id) {
            if (!this.likedComments.includes(id) && !this.dislikedComments.includes(id)) {
                api.dislikeComment(id)
                .then(response => {
                    this.comments.forEach(comment => {
                        if (comment.id === id) {
                            comment.likes = response.data.likes;
                            comment.dislikes = response.data.dislikes;
                        }
                    })
                    this.dislikedComments.push(id);
                })
                .catch(error => {
                    console.log(error);
                })
            } else if (!this.likedComments.includes(id) && this.dislikedComments.includes(id)) {
                api.unislikeComment(id)
                .then(response => {
                    this.comments.forEach(comment => {
                        if (comment.id === id) {
                            comment.likes = response.data.likes;
                            comment.dislikes = response.data.dislikes;
                        }
                    })
                    this.dislikedComments.splice(this.dislikedComments.indexOf(id), 1);
                })
                .catch(error => {
                    console.log(error);
                })
            } else if (this.likedComments.includes(id) && !this.dislikedComments.includes(id)) {
                api.dislikeAfterLikeComment(id)
                .then(response => {
                    this.comments.forEach(comment => {
                        if (comment.id === id) {
                            comment.likes = response.data.likes;
                            comment.dislikes = response.data.dislikes;
                        }
                    })
                    this.likedComments.splice(this.likedComments.indexOf(id), 1);
                    this.dislikedComments.push(id);
                }
                )
            } else {
                console.log('Error');
            }
        },
        deleteComment(id) {
            api.deleteComment(id)
            .then(
                this.comments = this.comments.filter(comment => comment.id !== id),
                this.likedComments = this.likedComments.filter(comment => comment !== id),
                this.dislikedComments = this.dislikedComments.filter(comment => comment !== id)
            ).catch(error => {
                console.log(error);
            })
        }
    }
})