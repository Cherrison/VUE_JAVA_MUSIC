 <template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h3>我都快复习不完了</h3>
    <h5> 啊啊啊! 我凉了</h5>
  </div>
</template>

<script>
export default {
    name: 'setting',
    data () {
            return {
            msg: "什么你还想要设置界面???"
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
.hello{
    display: flex;
    justify-content: center;
    text-align: center;
    align-items: center;
}
</style>

 
 
