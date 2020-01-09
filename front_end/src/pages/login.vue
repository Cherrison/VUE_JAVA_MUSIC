<template>
    <div class="login-container">
        <el-card header="请先登录" class="login-card">
            <el-form @submit.native.prevent="login">
                <el-form-item label="用户名" >
                    <el-input v-model="username"></el-input>
                </el-form-item>
                <el-form-item label="密码" >
                    <el-input v-model="password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" native-type="submit"> 登录 </el-button>
                    <el-button type="info" @click="register"> 没有账户 </el-button>
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
                username: 'oucouc',
                password: 'oucouc'
            }
        },
        mounted() {
            this.$message('请先登录')
        },
        methods:{
            async login(){
                if(this.username<3 || this.password.length<3){
                    this.$message({
                        message: '输入不合法',
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
                const res = await this.$axios.post('login', formData, config)
                try{
                    if(res.status === 200){
                        this.$message({
                            message: '登录成功',
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
            register(){
                this.$router.push('/register')
            },
            back(){
                this.$router.go(-1)
            }
        }
    }
</script>


// let formData = {
//                     username: this.username,
//                     password: this.password
//                 }
//                 let config = {
//                     headers: {
//                     'Content-Type': 'application/x-www-form-urlencoded'
//                     }
//                 }
//                 this.$axios.post('login', formData, config)
//                  .then(res => {
//                      console.log(res);
//                  })
//                  .catch(function (error) { 
//                     console.log(error);
//                 });