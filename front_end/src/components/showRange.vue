<template>
    <div>
      <div id="main" ref="chart" @click="handleClick"></div>
    </div>
</template>

<script>
var echarts = require("echarts");
export default {
  name: "charts",
  data() {
    return {
      data:"",
      rangeList:[]
     }},
  mounted() {
    let mychart = echarts.init(this.$refs.chart);
    
    mychart.showLoading();
    
    this.$axios.post('getRange')
                 .then(res => {
                    this.rangeList = res.data.rangeList
                    console.log(this.rangeList)
                    this.showRangeList(mychart, this.rangeList)
                    mychart.hideLoading();
                 })
                 .catch(function (error) { 
                    console.log(error);
                });
  },
  methods:{
      handleClick(params){
         
      },
      showRangeList(mychart, rangeList) {
        let dataAxis = []
        let data = []
        for (var music of rangeList) {
            dataAxis.push(music.name)
            data.push(music.times)
        }
        let yMax = data[0] + data[0] * 0.3;
        let dataShadow = [];
        for (var i = 0; i < data.length; i++) {
            dataShadow.push(yMax);
        }
            let self = this;
            self.data = {
            title: {
                text: '音乐播放量排行',
                subtext: 'PS: 点击或者滑动鼠标有意想不到的惊喜哦!',
                left: "center"
            },
            xAxis: {
                data: dataAxis,
                axisLabel: {
                    inside: true,
                    formatter:function(val){
                        return val.split("").join("\n");
                    },
                    textStyle: {
                        color: "green"
                    }
                },
                axisTick: {
                    show: true
                },
                axisLine: {
                    show: true
                },
                z: 10
            },
            yAxis: {
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            dataZoom: [
                {
                    type: 'inside'
                }
            ],
            series: [
                { // For shadow
                    type: 'bar',
                    itemStyle: {
                        color: 'rgba(0,0,0,0.05)'
                    },
                    barGap: '-100%',
                    barCategoryGap: '40%',
                    data: dataShadow,
                    animation: false
                },
                {
                    type: 'bar',
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ]
                        )
                    },
                    emphasis: {
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ]
                            )
                        }
                    },
                    data: data
                }
            ]
        };
     mychart.setOption(this.data);
     let zoomSize = 4
     mychart.on('click', (params)=>{
        console.log("click chart")
        mychart.dispatchAction({
            type: 'dataZoom',
            startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        })
    })
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
   #main {
       width: 80%;
       height: 600px;
       /* background-color:black;  */
       margin: 0 auto;
      }
</style>