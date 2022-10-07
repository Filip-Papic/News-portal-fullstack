<template>
    <div id="banner" :style="style">
        <div id="box" v-resize-text>
            <h2>{{ title }}</h2>
        </div>
        <div class="div-overlay"></div>
    </div>
</template>

<script>
    import ResizeText from 'vue-resize-text'
    
    export default {
        name: "Banner",
        directives: {
            ResizeText
        },
        props: {
            title: {
                //type: String,
                required: true
            },
            image: {
                type: Number,
                required: false,
                default: 378
            }
        },
        computed: {
            style() {
                return {
                    backgroundImage: `url(https://picsum.photos/1980/720/?image=${this.image})`
                }
            }
        }
    }
</script>

<style scoped>
    #banner {
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center;
        width: 100vw;
        position: relative;
        margin-left: -50vw;
        height: 20rem;
        left: 50%;
        margin-top: -3.8rem;
        z-index: 1;
    }

    .div-overlay {
        min-width: 100%;
        min-height: 24%;
        position: absolute;
        top: 10%;
        left: 50%;
        z-index: 3;
        transform: translate(-50%, -50%);
        background-color: #373D3F;
        -moz-opacity:.40;
        filter:alpha(opacity=40);
        opacity:.50;
        z-index: 2;
    }

    #box {
        background-color: rgba(0, 0, 0, 0.5);
        min-width: 30%;
        min-height: 30%;
        position: absolute;
        top: 60%;
        left: 50%;
        z-index: 3;
        transform: translate(-50%, -50%);
        color: #373D3F;
    }

    h2 {
        font-size: 5rem;
        font-weight: 700;
        margin: 0;
        color: rgba(255, 255, 255, 0.9);
        text-align: center;
    }
</style>