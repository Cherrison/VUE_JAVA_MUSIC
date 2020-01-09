<template>
    <div>
        
        <el-card header="上传歌单___uuid为自动生成" class="login-card">
            <el-form @submit.native.prevent="submit"  label-width="80px">
                <el-form-item label="Name" >
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item label="Creator" >
                    <el-input v-model="formData.creator"></el-input>
                </el-form-item>
                <el-form-item label="Picture" >
                    <el-upload
                        class="upload-demo"
                        action="uploadPicture"
                        :before-remove="beforeRemovePic"
                        :limit="1"
                        :on-success="handleAddPic"
                        :on-exceed="handleExceedPic"
                        :file-list="pictureFileList">
                        <el-button size="small" style="margin-left:10px;" type="primary">点击上传</el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="MiusicItems" >
                    <el-upload
                        class="upload-demo upload"
                        action="uploadMusic"
                        :before-remove="beforeRemoveMusic"
                        multiple
                        :limit="5"
                        :on-success="handleAddMusic"
                        :on-exceed="handleExceedMusic"
                        :file-list="musicFileList">
                        <el-button size="small" type="primary" style="margin-left:10px;float:left;">点击上传</el-button>
                    </el-upload>
                     <el-button type="info" native-type="submit" style="float:right;">
                            <i class="el-icon-upload el-icon--right"></i>上传歌单 
                        </el-button>
                </el-form-item>
              
            </el-form>
        </el-card>
    </div>
</template>

<style>
    .login-card{
        width: 40%;
        margin: 10rem auto;
        height: 60%;
    }
    .el-input{
        display: inline;
    }
    .upload-demo.upload .el-upload{
        width: 100%;
    }
</style>

<script>
    export default {
        data(){
            return {
                formData: {
                    uuid: 'md5md5666611111111',
                    name: 'test',
                    creator: 'cherry',
                    creatorId:'17090022030',
                    dateCreated: '',
                    picture: 'default.png',
                    musicItems:{}
                },
                pictureFileList:[],
                musicFileList:[],
            }
        },
        mounted() {
            // this.$message('欢迎注册')
        },
        methods:{
            submit(){
                let formData = new URLSearchParams();
                let _this = this;
                this.formData.dateCreated = new Date().format("yyyy-MM-dd hh:mm:ss");;
                //下面是表单绑定的data 数据
                for(let item in this.formData){
                    formData.append(item, this.formData[item]);
                }
                let config = {
                    headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }
                this.$axios.post('sheetUpload', JSON.stringify(this.formData), config)
                    .then(res=>{
                        
                    })
                    .catch(err=>{
                        console.log(err)
                    })
            },
            handleExceedPic(files, pictureFileList) {
                this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + pictureFileList.length} 个文件`);
            },
            beforeRemovePic(file, pictureFileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            handleExceedMusic(files, musicFileList) {
                this.$message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + musicFileList.length} 个文件`);
            },
            beforeRemoveMusic(file, musicFileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            handleAddPic(res,file,pictureFileList){
                console.log(file)
                this.formData.uuid = file.response.uuid
                this.formData.picture = this.getExitName(file.name)
            },
            handleAddMusic(res,file,musicFileList){
                this.formData.musicItems[file.response.uuid] = file.name
                console.log(file.response.uuid)
            },
            getExitName(name){
                return name.substr(name.indexOf('.'))
            }
        }
    }
</script>