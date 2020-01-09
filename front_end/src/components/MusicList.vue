<template>

    <div>
        <h1>MusicList</h1>
        <div class="my-3 p-3 bg-white rounded shadow-sm" v-for="musicsheet in musicsheets">
				<h4 class="border-bottom border-gray pb-2 mb-1">{{musicsheet.name}}</h4>
				<p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
					<strong class="d-block text-gray-dark">Creator:
						{{musicsheet.creator}}</strong> <strong class="d-block text-gray-dark">Date
						created: {{musicsheet.dateCreated}}</strong>
				</p>

				<table class="table table-sm">
					<thead>
						<tr>
							<th>Name</th>
							<th>MD5</th>
							<th>Operation</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="name, md5 in musicsheet.musicItems">
							<td>{{name}}</td>
							<td>{{md5}}</td>
							<td>
                                <button type="button" class="btn btn-info btn-sm"
									v-on:click="loadAndPlay(name, md5)">PLAY</button>
                            </td>
						</tr>
					</tbody>
				</table>
			</div>
    </div>
    
</template>

<script>
export default {
    name: 'MusicList',
    data () {
		return {
		    	musicsheets: null
		    }	
		},
    mounted () {
		    axios.get('http://service.uspacex.com/music.server/queryMusicSheets?type=top20')
                 .then(response => (this.musicsheets = response.data.musicSheetList))
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
		  	}
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
