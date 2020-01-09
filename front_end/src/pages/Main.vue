<template>
    <div>
        <div class="header my">
            <router-link to="/index">首页</router-link>
            <router-link to="/userCenter/userInfo">个人中心</router-link>
            <router-link to="/musicList">音乐列表</router-link>
            <router-link to="/musicplayer">音乐播放器</router-link>
            <!-- <router-link class="login" to="/login">登录</router-link> -->
            <el-button size="mini" @click="login" type="primary" icon="{status_icon}">{{status}}</el-button>
        </div>
        <router-view class="header-top"></router-view>
    </div>
</template>

<style>
.header-top{
    padding-top: 50px;
}

</style>

<script>
    export default {
        data(){
            return {
                val: '',
                status : "登录",
                status_icon: ''
            }
        },
        mounted() {
            this.val = localStorage.token || ''
            if(!this.val) {
                    this.status="登录"
                    this.status_icon= 'el-icon-delete'
            }else{
                this.status="退出登录"
                this.status_icon= 'el-icon-s-promotion'
            }
        },
        methods:{
            login(){
                if(!this.val)
                    this.$router.push('/login')
                else{
                    this.val = ''
                    localStorage.token = ''
                    this.status="登录"
                    this.status_icon= 'el-icon-delete'
                    this.logout()
                    this.$router.push('/index')
                }
            },
            logout(){
                this.$axios.post('logout')
                    .then(res=>{
                        if(res.status==200)
                            this.$message({
                                message: '退出成功',
                                type: 'success'
                            })
                    })
                    .catch(function (error) { 
		    	        console.log(error);
		    	    });
            }
        }
    }
</script>