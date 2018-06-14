/**
 * @file ImageEditor.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/13/18
 */
<style lang="less">
 @import 'ImageEditor.less';
</style>
<template>
<div class="image-editor">
  <Row>
    <Col span="14" class="image-editor-con1">
      <div class="cropper">
        <img id="cropimg" alt="">
      </div>
    </Col>
    <Col span="10" class="image-editor-con1">
      <Row type="flex" justify="center" align="middle" class="image-editor-con1-preview-con" style="margin-left: 5px">
        <div id="preview"></div>
      </Row>
      <div class="image-editor-con1-btn-con" style="margin: 10px 5px">
        <input type="file" accept="image/png, image/jpeg, image/gif, image/jpg" @change="handleChange" id="fileinput" class="fileinput" />
        <label class="filelabel" for="fileinput"><Icon type="image"></Icon>&nbsp;选择图片</label>
        <span><Button @click="handlecrop" type="primary" icon="crop" style="margin-top: -1px">预览</Button></span>
        <span><Button @click="handSubmit" type="success" icon="crop" style="margin-top: -1px">提交</Button></span>
      </div>
      <Modal v-model="option.showCropedImage">
        <p slot="header">预览裁剪之后的图片</p>
        <img :src="option.cropedImg" alt="" v-if="option.showCropedImage" style="width: 100%;">
      </Modal>
    </Col>
  </Row>
</div>
</template>
<script>
import Cropper from 'cropperjs';
import util from '@/libs/util';
import './cropper.min.css';
export default {
  name: 'ImageEditor',
  data () {
    return {
      cropper: {},
      option: {
        showCropedImage: false,
        cropedImg: ''
      }
    };
  },
  methods: {
    handleChange (e) {
      let file = e.target.files[0];
      let reader = new FileReader();
      reader.onload = () => {
        this.cropper.replace(reader.result);
        reader.onload = null;
      };
      reader.readAsDataURL(file);
    },
    handlecrop () {
      let file = this.cropper.getCroppedCanvas().toDataURL();
      this.option.cropedImg = file;
      this.option.showCropedImage = true;
    },
    handSubmit () {
      let file = this.cropper.getCroppedCanvas().toDataURL();
      console.log(file);
//      util.post('upload/', file).then (res => {
//        this.$Message.success('upload success');
//      });
    }
  },
  mounted () {
    let img = document.getElementById('cropimg');
    this.cropper = new Cropper(img, {
      dragMode: 'move',
      preview: '#preview',
      restore: false,
      center: false,
      highlight: false,
      cropBoxMovable: false,
      toggleDragModeOnDblclick: false
    });
  }
}
</script>
