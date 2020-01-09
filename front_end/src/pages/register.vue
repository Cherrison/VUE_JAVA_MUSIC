<template>
    <div class="login-container">
        <el-card header="注册账号" class="login-card">
            <el-form @submit.native.prevent="register">
                <el-form-item label="用户名" >
                    <el-input v-model="username"></el-input>
                </el-form-item>
                <el-form-item label="密码" >
                    <el-input v-model="password" show-password></el-input>
                </el-form-item>
                 <el-form-item label="再次输入密码" >
                    <el-input v-model="passwordAgain" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" native-type="submit"> 注册 </el-button>
                    <el-button type="danger" @click="back" > 取消 </el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<style>
    .login-card{
        width: 20%;
        margin: 10rem auto;
    }
</style>

<script>
    export default {
        data(){
            return {
                username: '',
                password: '',
                passwordAgain: ''
            }
        },
        mounted() {
            this.$message('欢迎注册')
        },
        methods:{
            async register(){
                if(this.username<3 || this.password.length<3){
                    this.$message({
                        message: '输入不合法',
                        type: 'error'
                    })
                    return
                }
                if(this.passwordAgain != this.password){
                    this.$message({
                        message: '两次输入密码不一致',
                        type: 'error'
                    })
                    return
                }
                let formData = new URLSearchParams();
                let _this = this;
                //下面是表单绑定的data 数据
                formData.append('username', this.username);
                formData.append('password', this.password);
                let config = {
                    headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }
                const res = await this.$axios.post('register', formData, config)
                try{
                    if(res.status === 200){
                        this.$message({
                            message: '注册成功',
                            type: 'success'
                        })
                        localStorage.token = true
                        this.$router.push('/index')
                    }
                    else{
                        console.log(res.status)
                    }
                }catch(err){
                    console.log("登录出错了")
                }
            },
            back(){
                this.$router.go(-1)
            }
        }
    }
</script>