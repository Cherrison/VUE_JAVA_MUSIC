<template>
    <div  
	class="loading-size"
	v-loading="loading"
	element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
	>
    	<div role="main" class="container">
			<el-button  class="change-env" @click="changeEnv" type="success" round>{{env}}</el-button>
			<div id="app" class="mt-3">
				<div class="display-sheet">
						<div class="sheet my-3 p-3 bg-white rounded shadow-sm" v-for="(musicsheet, index) in musicsheets" v-bind:key="index">
						<h4 class="play-name border-bottom border-gray pb-2 mb-1">
							{{musicsheet.name}} 
							<button type="button" class="btn btn-info btn-sm play-btn-pos" @click="loadAndPlay(index)">PLAY</button>
						</h4>
						<p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
							<strong class="d-block text-gray-dark">Creator: {{musicsheet.creator}}</strong> 
							<strong class="d-block text-gray-dark">Date created: {{musicsheet.dateCreated}}</strong>
						</p>
						<table class="table table-sm">
							<thead>
								<tr>
									<th>Name</th>
									<th>MD5</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="(name, md5, index) in musicsheet.musicItems" v-bind:key="index">
									<td style="overflow:hidden;">{{name}}</td>
									<td>{{md5}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
    	</div>
	
    </div>
</template>

<script>
export default {
	name: 'MusicList',
    data () {
		return {
				musicsheets: null,
				loading: true,
				isOnline: true,
				env:"线下",
				Loacl:{
					downloadPicture: "downloadPicture?uuid=",
					music: "music?md5=",
					mList: "queryMusicSheets?type=top20"
				},
				Online:{
					downloadPicture: "http://service.uspacex.com/music.server/downloadPicture?uuid=",
					music: "http://service.uspacex.com/music.server/music?md5=",
					mList: "http://service.uspacex.com/music.server/queryMusicSheets?type=top20"
				}
		    }
		},
		mounted () {
				this.initMusic(this.Online)
			},
		methods: {
				loadAndPlay: function (index) {
					this.$emit('pls',index);
				},
				initMusic(env){
					this.loading = true;
					this.$axios.get(env.mList)
					.then(res => {
						this.musicsheets = res.data.musicSheetList;
						this.loading = false;
						let musicsheet = this.musicsheets;
						let sendData = [];
						for(let i=0;i<musicsheet.length;i++){
							let alist = [];
							let musicItems = musicsheet[i].musicItems;
							let picmd5 = musicsheet[i].picture;
							let num = picmd5.indexOf(".");
							picmd5 = picmd5.substr(0, num);
							for (var item in musicItems) {
								let music = {};
								music.pic = env.downloadPicture + picmd5;
								let detail = musicItems[item].split("-");
								if(detail.length>1){
									music.artist  = detail[0];
									music.title = detail[1];
								}else{			
									music.artist  = "无";
									music.title = musicItems[item];
								}
								music.src = env.music + item;
								alist.push(music);
							}
							sendData.push(alist);
						}
						this.$emit('cgl',sendData);
					})
					.catch(function (error) { 
						console.log(error);
					});
				},
				changeEnv(){
					if(this.isOnline == true){
						this.isOnline = false;
					}else{
						this.isOnline = true;
					}
            	},
		},
		watch: {
			isOnline(val){
				if(val){
					this.env = "线下"
					this.initMusic(this.Online)
					console.log("Online")
				}else{
					this.env = "线上"
					this.initMusic(this.Loacl)
					console.log("Local")
				}
				
			}
		}
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.loading-size{
	height: 100vh;
}
.play-btn-pos{
	right: -30px;
	position: relative;
}
.play-name{
	width: 100%;
}
.container{
	margin-left: 10px;
	width: 600px;
}
.sheet{
	margin-left: 10px;
	width: 600px;
	height:410px;
	border: 2px sandybrown solid;
	border-radius: 15px;
	overflow-y: hidden;
}
.container{
	margin-left: 10px;
}
.display-sheet{
	display: flex;
	width: 100vw;
	flex-wrap: wrap;
}
.change-env{
	position: absolute; 
	top: 130px;
}
</style>
