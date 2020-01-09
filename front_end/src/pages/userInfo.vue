<template>
    <div  
	class="loading-size"
	v-loading="loading"
	element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
	>
    	<div role="main" class="container"  >
			<div class="mt-3">
				<div class="display-sheet">
						<div class="sheet my-3 p-3 bg-white rounded shadow-sm" v-for="(musicsheet, index) in musicsheets" v-bind:key="index">
						<h4 class="play-name border-bottom border-gray pb-2 mb-1">
							{{musicsheet.name}} 
							<button type="button" class="btn btn-info btn-sm play-btn-pos" @click="deleteSheet(index)">Delete</button>
						</h4>
						<p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
							<strong class="d-block text-gray-dark">Date created: {{musicsheet.dateCreated}}</strong>
						</p>
						<table style="font-size:15px;" class="table table-sm">
							<thead>
								<tr>
									<th>Name</th>
									<th>MD5</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="(name, md5, index) in musicsheet.musicItems" v-bind:key="index">
									<td style="overflow:hidden;">{{name}}</td>
									<td id="md5">{{md5}}</td>
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
				Looding: '加载中...',
				loading: true
		    }
		},
    mounted () {
		    this.$axios.get('queryMusicSheets?type=top20')
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
							music.pic = "http://service.uspacex.com/music.server/downloadPicture?uuid=" + picmd5;
							let detail = musicItems[item].split("-");
							if(detail.length>1){
								music.artist  = detail[0];
								music.title = detail[1];
							}else{			
								music.artist  = "无";
								music.title = musicItems[item];
							}
							music.src = 'http://service.uspacex.com/music.server/music?md5=' + item;
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
	methods: {
		  	deleteSheet(index) {
                let _this = this;
                let formData = new URLSearchParams();
                formData.append("sheetId", _this.musicsheets[index].id);
                _this.$axios.post('/delSheet', formData)
                        .then(res=>{
                            console.log(_this.musicsheets[index].id)
                            _this.musicsheets.splice(index, 1)
                           if(res.status == 200){
                               _this.$message({
                                    message:"删除成功!",
                                    type: 'success'
                                })
                           }
                        })
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
	width: 100%;
}
.sheet{
	margin-left: 10px;
	width: 410px;
	height: 320px;
	border: 2px sandybrown solid;
	border-radius: 15px;
	overflow-y: hidden;
}
h4{
    margin-top: 2px;
}
#md5{
    user-select:text;
}
.display-sheet{
	display: flex;
    justify-content: space-between;
    margin-top: 3%;
	width: 80vw;
	flex-wrap: wrap;
}
 /* 伪元素 */
.display-sheet::after{
    content: '';
    width: 400px;
}
</style>
