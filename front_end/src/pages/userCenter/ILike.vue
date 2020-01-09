<template>
    <div>
		<audio id="musicplayer" controls v-show="false">
                <source id="musicsource" src="http://service.uspacex.com/music.server/music?md5=77008b41f4c692808ac7b414722269e0" type="audio/mpeg">
        </audio>
    	<div role="main" class="container">
							<el-pagination 
								id="info"
								style="position:fixed;right:40%;bottom:15vh;"
								background
								layout="prev, pager, next"
								:page-size="7"
								:total="30">
							</el-pagination>
			<div class="display-sheet">
			<div
				class="sheet my-3 p-3 bg-white rounded shadow-sm" 
				v-loading="loading"
				element-loading-text="拼命加载中"
				element-loading-spinner="el-icon-loading"
				element-loading-background="rgba(0, 0, 0, 0.8)"
			>
					<h4 style="margin-top:3px;text-align:center;" class="border-bottom border-gray pb-2 mb-1">这里是我喜欢的歌曲啊</h4>
					<p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
						<strong class="d-block text-gray-dark">Status:Play_Online</strong> 
					</p>
					<table class="table" style="overflow:hidden;">
						<thead>
							<tr>
								<th>Name</th>
								<th>MD5</th>
								<th>Operation</th>
							</tr>
						</thead>
						<tbody v-for="(musicsheet, index) in musicsheets" v-bind:key="index">
							<tr v-for="(name, md5, index) in musicsheet.musicItems" v-bind:key="index">
								<td style="overflow:hidden;">{{name}}</td>
								<td>{{md5}}</td>
								<td style="display:flex; justify-content: space-around;">
									<el-button type="primary" round size="small"
										@click="loadAndPlay(name, md5)">Play</el-button>
								</td>
							</tr>
						</tbody>
					</table>
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
				loading: true
		    }	
		},
    mounted () {
		    this.$axios.get('http://service.uspacex.com/music.server/queryMusicSheets?type=top20')
                 .then(res => {
                    this.musicsheets = res.data.musicSheetList;
					let arr = this.musicsheets;
					this.loading = false;
                 })
                 .catch(function (error) { 
                    console.log(error);
                });
		},
	methods: {
		  	loadAndPlay: function (name, md5) {
		  		console.log("Load and Play." + name + "(" + md5 + ")");
		  		document.getElementById('musicsource').src = 'http://service.uspacex.com/music.server/music?md5=' + md5;
		  		document.getElementById('musicplayer').load();
				document.getElementById('musicplayer').play();

				let formData = new URLSearchParams();
				formData.append("md5value", md5);
				formData.append("name", name);
				this.$axios.post('/musicPlay', formData)
					.then(res => {
						console.log(res)
					})
			},
			handleLike(md5){
				let formData = new URLSearchParams();
                formData.append("md5value", md5);
				this.$axios.post('/musicLike', formData)
					.then(res => {
						this.$message({
                                    message:"Like成功!",
                                    type: 'success'
								})
						console.log(res)
					})
			}
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#info::before{
	content: ""
}
.sheet{
	margin-left: 10px;
	width: 80%;
	height: 70vh;
	border: 2px sandybrown solid;
	border-radius: 15px;
	overflow-y: scroll;
}
.container{
	margin-left: 10px;
}
.display-sheet{
	display: flex;
	width: 100vw;
	flex-wrap: wrap;
}
</style>
