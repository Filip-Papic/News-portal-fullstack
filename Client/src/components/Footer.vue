<template>
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="footer-widget">
                        <div class="footer-logo">
                            <a href="index.html"><img src="@/assets/logo.png" alt="" width="60" height="60"/></a>
                        </div>
                        <div class="footer-social">
                            <a href=""><i class="fab fa-twitter"></i></a>
                            <a href=""><i class="fab fa-facebook-f"></i></a>
                            <a href=""><i class="fab fa-youtube"></i></a>
                            <a href=""><i class="fab fa-instagram"></i></a>
                            <a href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: "Footer",
}
</script>

<style>

    footer {
        background: #373D3F;
        padding: 50px 0;
    }

    
    footer .footer-logo img {
        max-width: 200px;
        margin-bottom: 20px;
    }

    footer .footer-social a {
        display: inline-block;
        width: 40px;
        height: 40px;
        line-height: 40px;
        text-align: center;
        border-radius: 50%;
        background: #007E33;
        color: #fff;
        margin-right: 10px;
        transition: all 0.3s ease 0s;
    }

    footer .footer-social a:hover {
        background: #00C851;
        color: success;
    }

</style>