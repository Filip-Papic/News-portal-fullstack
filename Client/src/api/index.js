import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8081/api"
});

api.interceptors.request.use(
    config => {
        const token = localStorage.getItem("jwt");
        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        Promise.reject(error);
    }
);

//auth
export const login = (email, password) => {
    return api.post("/users/login", { email, password })
};

//users
export const getUsers = (currentPage, perPage) => {
    return api.get(`/cms/users/page/${currentPage}/${perPage}`);
};

export const getUser = (id) => {
    return api.get(`/cms/users/${id}`);
};

export const createUser = (user) => {
    return api.post("/cms/users", user);
};

export const updateUser = (id, user) => {
    return api.put(`/cms/users/${id}`, user);
};

export const changeStatus = (id) => {
    return api.put(`/cms/users/status/${id}`, {});
};

export const deleteUser = (id) => {
    return api.delete(`/cms/users/${id}`);
};

//news
export const getNews = (currentPage, perPage) => {
    return api.get(`/news/page/${currentPage}/${perPage}`);
};

export const getNewsById = (id) => {
    return api.get(`/news/${id}`);
};

export const getNewsByCategory = (category, currentPage, perPage) => {
    return api.get(`/news/category/${category}/page/${currentPage}/${perPage}`);
};

export const getNewsWithTag = (tag, currentPage, perPage) => {
    return api.get(`/news/tag/${tag}/page/${currentPage}/${perPage}`);
};

export const getNewNews = () => {
    return api.get(`/news/new`);
};

export const getPopularNews = () => {
    return api.get(`/news/popular`);
};

export const getSimilarNews = (id, currentPage, perPage) => {
    return api.get(`/news/similar/${id}/${currentPage}/${perPage}`);
};

export const searchNews = (search, currentPage, perPage) => {
    return api.get(`/news/search/${search}/page/${currentPage}/${perPage}`);
};

export const createNews = (news) => {
    return api.post("/news", news);
};

export const updateNews = (id, news) => {
    return api.put(`/news/${id}`, news);
};

export const updateNewsViews = (id) => {
    return api.put(`/news/view/${id}`, {});
};

export const updateNewsLikes = (id) => {
    return api.put(`/news/like/${id}`, {});
};

export const updateNewsDislikes = (id) => {
    return api.put(`/news/dislike/${id}`, {});
};

export const likeAfterDislike = (id) => {
    return api.put(`/news/like-after-dislike/${id}`, {});
};

export const dislikeAfterLike = (id) => {
    return api.put(`/news/dislike-after-like/${id}`, {});
};

export const unlike = (id) => {
    return api.put(`/news/unlike/${id}`, {});
};

export const undislike = (id) => {
    return api.put(`/news/undislike/${id}`, {});
};

export const deleteNews = (id) => {
    return api.delete(`/news/${id}`);
};

//categories

export const getCategories = () => {
    return api.get("/category");
};

export const getCategory = (id) => {
    return api.get(`/category/${id}`);
};

export const createCategory = (category) => {
    return api.post("/cms/category", category);
};

export const updateCategory = (id, category) => {
    return api.put(`/cms/category/${id}`, category);
};

export const deleteCategory = (id) => {
    return api.delete(`/cms/category/${id}`);
};

//tags

export const getTags = () => {
    return api.get("/tags");
}

export const getTagsByNewsId = (id) => {
    return api.get(`/tags/tags-for-news/${id}`);
}

export const getTagByName = (name) => {
    return api.get(`/tags/name/${name}`);
}

export const getPopularTags = () => {
    return api.get(`/tags/popular`);
};

export const addTag = (tag) => {
    return api.post("/tags", tag);
};

export const addTagToNews = (id, tag) => {
    return api.post(`/tags/tags-for-news/${id}/${tag}`, {});
}

export const removeTagFromNews = (id, tag) => {
    return api.delete(`/tags/tags-for-news/${id}/${tag}`);
}

export const deleteTag = (id) => {
    return api.delete(`/tags/${id}`);
};

//comments

export const getCommentsByNewsId = (id) => {
    return api.get(`/comment/news/${id}`);
};

export const addComment = (comment) => {
    return api.post("/comment", comment);
};

export const likeComment = (id) => {
    return api.put(`/comment/like/${id}`, {});
};

export const dislikeComment = (id) => {
    return api.put(`/comment/dislike/${id}`, {});
};

export const likeAfterDislikeComment = (id) => {
    return api.put(`/comment/like-after-dislike/${id}`, {});
};

export const dislikeAfterLikeComment = (id) => {
    return api.put(`/comment/dislike-after-like/${id}`, {});
};

export const unlikeComment = (id) => {
    return api.put(`/comment/unlike/${id}`, {});
};

export const undislikeComment = (id) => {
    return api.put(`/comment/undislike/${id}`, {});
};

export const deleteComment = (id) => {
    return api.delete(`/comment/${id}`);
};