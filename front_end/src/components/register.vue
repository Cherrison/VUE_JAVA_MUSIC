<template>
    <div>
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="#">用户登录</a>
        </nav>

        <div class="container">
            <div class="row mt-4">
                <div class="col-md-5">
                    <form :action="login_url" method="POST"  @submit.prevent="submit">
                        <div class="form-group">
                            <label for="input-message">用户名</label> <input type="text"
                                class="form-control" id="input-username" name="username"
                                placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="input-message">密码</label> <input type="password"
                                class="form-control" id="input-password" name="password"
                                placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="input-message">确认密码</label> <input type="password"
                                class="form-control" id="input-password" name="password"
                                placeholder="">
                        </div>
                        <button type="submit" class="btn btn-primary">登 录</button>
                        <a class="btn btn-success" href="register.html" role="button">没有账户</a>
                        <a class="btn btn-secondary" href="#" role="button">取
                            消</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<style>

</style>

<script>
    export default {
        data(){
            return {
               
            }
        },
        methods:{
            submit: function(e){
                console.log(e);
            }
        }
    }
</script>