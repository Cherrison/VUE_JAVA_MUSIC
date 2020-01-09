<template>

    <div>
		<musicListTitle></musicListTitle>
		<audio id="musicplayer" controls v-show="false">
                <source id="musicsource" src="http://service.uspacex.com/music.server/music?md5=77008b41f4c692808ac7b414722269e0" type="audio/mpeg">
        </audio>
			<div role="main" id="show-range">
								
				<showRange style="width: 50vw;"></showRange>
				<div class="display-sheet">
					<div 
						class="sheet my-3 p-3 bg-white rounded shadow-sm" 
						v-loading="loading"
						element-loading-text="拼命加载中"
						element-loading-spinner="el-icon-loading"
						element-loading-background="rgba(0, 0, 0, 0.8)">
							<h4 style="margin-top:3px;text-align:center;" class="border-bottom border-gray pb-2 mb-1">全部的歌曲都在这里啦</h4>
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
											<el-button type="danger" round size="small"
												@click="handleLike(md5)">Like</el-button>
										</td>
									</tr>
								</tbody>
							</table>
					</div>
			</div>
    	</div>
		<el-pagination 
			id="info"
			style="position:fixed;right:5%;top:30vh;"
			background
			layout="prev, pager, next"
			:page-size="7"
			:total="30">
		</el-pagination>
    </div>
</template>

<script>
import musicListTitle from '../components/musicListTitle'
import showRange from '../components/showRange'

export default {
	name: 'MusicList',
	  components: {
	  musicListTitle,
	  showRange
    },
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
			fmtName(name) {
				let num = name.indexOf(".");
				if (num != -1)
					name = name.substr(0, num);
				let fullName = name.split("-");
				if (fullName.length > 1) {
					name = fullName[1];
				}
				return name.trim();
			},
		  	loadAndPlay: function (name, md5) {
		  		console.log("Load and Play." + name + "(" + md5 + ")");
		  		document.getElementById('musicsource').src = 'http://service.uspacex.com/music.server/music?md5=' + md5;
		  		document.getElementById('musicplayer').load();
				document.getElementById('musicplayer').play();
				
				let formData = new URLSearchParams();
				formData.append("md5value", md5);
				formData.append("name", this.fmtName(name));
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
<style>
.range-title{
	display: flex;
	align-items: center;
}
#info::before{
	content: ""
}
.sheet{
	margin-left: 10px;
	width: 80%;
	height: 58vh;
	border: 2px sandybrown solid;
	border-radius: 15px;
	overflow-y: scroll;
}

#show-range{
	display: flex;
	justify-content: space-between;
}
.display-sheet{
	display: flex;
	width: 60vw;
	flex-wrap: wrap;
}
</style>
