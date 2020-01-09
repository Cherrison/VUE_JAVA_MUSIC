<template>
    <div>
        <el-row class="tac">
            <div class="tac-content">
                <router-view class="content"></router-view>
            </div>
            <el-col :span="3" style="position: fixed;" class="full" >
                <el-menu default-active="1" class="el-menu-vertical-demo parent full" @open="handleOpen" @close="handleClose" background-color="#545c64" text-color="#fff" :collapse="isCollapse" active-text-color="#ffd04b">
                    <router-link to="/userCenter/userInfo">
                        <el-menu-item index="1">
                            <i class="el-icon-user"></i>
                            <span slot="title">我的歌单</span>
                        </el-menu-item>
                    </router-link>
                    <router-link to="/userCenter/ilike">
                         <el-menu-item index="2">
                            <i class="el-icon-star-off"></i>
                            <span slot="title">我喜欢</span>
                        </el-menu-item>
                    </router-link>
                    <router-link to="/userCenter/createSheet">
                        <el-menu-item index="3">
                            <i class="el-icon-document"></i>
                            <span slot="title">创建歌单</span>
                        </el-menu-item>
                    </router-link>
                    <router-link to="/userCenter/setting">
                        <el-menu-item index="4">
                            <i class="el-icon-setting"></i>
                            <span slot="title">设置</span>
                        </el-menu-item>
                    </router-link>
                   
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-menu"></i>
                            <span>更多</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">待开发一</template>
                            <el-menu-item index="5-1">选项1</el-menu-item>
                            <el-menu-item index="5-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="待开发2">
                            <el-menu-item index="5-3">选项3</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-radio-group class="bottom" v-model="isCollapse">
                        <el-radio-button :label="false" v-if="isCollapse">展开</el-radio-button>
                        <el-radio-button :label="true" v-if="!isCollapse">收起</el-radio-button>
                    </el-radio-group>
                </el-menu>
            </el-col>
        </el-row>
    </div>
</template>

<style>
.content{
    height: 90vh;
    display: flex;
}
.tac-content{
    float: right;
    width: 86.3%;
}
.tac{
   height: 100%;
}
.header-top{
    height: 100vh;
}
.full{
    height: 100%;
}
.bottom{
   position: absolute;
   bottom: 50px;
}
.parent{
    position: relative;
}
</style>

<script>
  export default {
    data(){
        return {
            isCollapse: false
        }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>