/**
 * @file ImageEditor.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/13/18
 */
<template>
<div class="image-editor">
  <Row>
    <Col span="14" class="image-editor-con1">
      <div>
        <img id="cropimg" alt="">
      </div>
    </Col>
    <Col span="10">
      <Row type="flex" justify="center" align="middle">
        <div id="preview"></div>
      </Row>
      <div>
        <input type="file" accept="image/png, image/jpeg, image/gif, image/jpg" @change="handleChange" id="fileinput" class="fileinput" />
        <label class="filelabel" for="fileinput"><Icon type="image"></Icon>&nbsp;选择图片</label>
        <span><Button @click="handlecrop" type="primary" icon="crop">裁剪</Button></span>
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

<style lang="less">

</style>
