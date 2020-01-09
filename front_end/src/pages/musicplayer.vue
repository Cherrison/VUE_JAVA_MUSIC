<template>
    <div style="text-align: center;">
    <div class="title center-align">
        <i class="play-title fa fa-music"></i>&nbsp;&nbsp;听听音乐
    </div>
    <div class="music-container container ">
        <aplayer :music="music" :list="alist" :mini="ismini"></aplayer>
    </div>
    <el-button  @click="changeMini" type="info" round>{{control_mini}}</el-button>
    <div class="music-list">
        <musicListBody @cgl="changeList" @pls="playSheet"></musicListBody>
    </div>
</div>

</template>

<style>
@import "../assets/css/musicplayer.css";
.play-title{
    color: green;
}
.title{
    font-weight: bold
}
.music-container{
    width: 540px;
    position: fixed;
    right: 2%;
    top: 10%;
}
.music-list{
    left: 0px;
}
@media only screen and (max-width: 878px) {
    .music-container{
        bottom: 5%;
        top: unset;
    }
}
</style>
<script>
import aplayer from 'vue-aplayer'
import musicListBody from '../components/musicListBody'
export default {
    name: '',
    components: {
      aplayer,
      musicListBody
    },
    data() {
        return {
            control_mini: '最小化',
            ismini: false,
            playData: [],
            music: {
                title: '阴天快乐',
                artist: '陈奕迅',
                src: 'https://www.cheery.pro/music/565b_515b_0109_a1aa02bb57f1bdf722e06c29a5be07d5.m4a',
                pic : "http://service.uspacex.com/music.server/downloadPicture?uuid=2a2fa72e16c640d6a1e23a5cce67e98d"
            },  
            alist: [
                    {
                        title: '阴天快乐',
                        artist: '陈奕迅',
                        url: 'https://www.cheery.pro/music/565b_515b_0109_a1aa02bb57f1bdf722e06c29a5be07d5.m4a',
                        pic : "http://p1.music.126.net/EES1U3UVWUdt_tHyiY8XAw==/5962651557619306.jpg?param=130y130"
                    },
                        {
                        title: 'インドア系ならトラックメイカ',
                        artist: 'Yunomi',
                        url: 'https://www.cheery.pro/music/5409_055a_530f_cc9d71fe0820fe8fc2b4dece9cd01d8d.m4a',
                        pic : "http://p2.music.126.net/Ez-_OKKKzKsSXzDY15hwTw==/3394192418636766.jpg?param=130y130"
                    },
                        {
                        title: '飘向远方',
                        artist: '黄明志 / G.E.M.邓紫棋',
                        url: 'https://www.cheery.pro/music/7993fdff33153cf32e42e2f441502a0a.mp3',
                        pic : "http://p2.music.126.net/v7XfwRk13PWu0SN30UDSjg==/109951163137495030.jpg?param=130y130"
                    }
            ]
        }
    },
        methods: {
            changeMini: function (){
                if(this.ismini == true){
                    this.ismini = false;
                }else{
                     this.ismini = true;
                }
            },
            changeList: function (data){
                this.playData = data;
            },

            playSheet: function (sheetId){
                console.info(this.alist);console.info(this.alist);
                this.alist = this.playData[sheetId];
                this.music =  this.alist[0];
                console.info(this.alist);
            }
        },
        watch: {
            ismini(val) {
                if(val) 
                    this.control_mini="恢复"
                else
                    this.control_mini="最小化"
            }
        }
}
</script>
