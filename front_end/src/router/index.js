// import Vue from 'vue'
// import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
// import MusicList from '@/components/MusicList'
// import Home from '@/components/Home'
// Vue.use(Router) // 实现“懒加载组件”的通用函数 function component(comp) { return
//     () =>
//     import (`@/components/${comp}`)
// }

// function view(comp) { return () =>
//         import (`@/view/${comp}`) }
// export default new Router({
//     routes: [{ path: '/', name: 'HelloWorld', component: HelloWorld }, {
//         path: '/music',
//         name: 'MusicList',
//         component: MusicList
//     }, { path: '/home', component: view('Home') }]
// })