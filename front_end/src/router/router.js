import Vue from 'vue'
import Router from 'vue-router'
import Main from '../pages/Main'
import musicListBody from '../components/musicListBody'
import Index from '../pages/index'
import UserCenter from '../pages/userCenter'
import UserInfo from '../pages/userInfo'
import ILike from '../pages/userCenter/ILike'
import Setting from '../pages/userCenter/setting'
import CreateSheet from '../pages/userCenter/createSheet'
import musicList from '../pages/musicList'
import musicplayer from '../pages/musicplayer'
import Login from '../pages/login'
import Register from '../pages/register'
import RangeTest from '../pages/rangeTest'
Vue.use(Router)

const router = new Router({
    routes: [{
            path: '/',
            name: 'Main',
            component: Main,
            children: [{
                path: 'userCenter',
                name: 'userCenter',
                component: UserCenter,
                children: [
                    { path: 'userInfo', name: 'userInfo', component: UserInfo },
                    { path: 'createSheet', name: 'createSheet', component: CreateSheet },
                    { path: 'iLike', name: 'iLike', component: ILike },
                    { path: 'setting', name: 'setting', component: Setting }
                ]
            }, {
                path: '/musicList',
                name: 'musicList',
                component: musicList,
                meta: { isPublic: true }
            }, {
                path: '/musicplayer',
                name: 'musicplayer',
                component: musicplayer,
                children: [{
                    path: 'musicList',
                    name: 'musicList',
                    component: musicListBody
                }],
                meta: { isPublic: true }
            }, { path: '/index', name: 'index', component: Index, meta: { isPublic: true } }]
        },
        { path: '/login', name: 'login', component: Login, meta: { isPublic: true } },
        { path: '/register', name: 'register', component: Register, meta: { isPublic: true } },
        { path: '/rangeTest', name: 'rangeTest', component: RangeTest, meta: { isPublic: true } }
    ]
})

router.beforeEach((to, from, next) => {
    if (!to.meta.isPublic && !localStorage.token) {
        return next('/login')
    }
    next()
})

export default router