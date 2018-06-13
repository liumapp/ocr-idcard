/**
 * @file ImageEditor.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/13/18
 */
<template>
<div>
  <Row>
    <Col span="14">
      <div>
        <img id="cropimg" alt="">
      </div>
    </Col>
    <Col span="10">
      <Row type="flex" justify="center" align="middle">
        <div id="preview"></div>
      </Row>
      <div>
        <input type="file" accept="image/png, image/jpeg, image/gif, image/jpg" @change="handleChange1" id="fileinput1" class="fileinput" />
        <label class="filelabel" for="fileinput1"><Icon type="image"></Icon>&nbsp;选择图片</label>
        <span><Button @click="handlecrop1" type="primary" icon="crop">裁剪</Button></span>
      </div>
      <Modal v-model="option1.showCropedImage">
        <p slot="header">预览裁剪之后的图片</p>
        <img :src="option1.cropedImg" alt="" v-if="option1.showCropedImage" style="width: 100%;">
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
      cropper: {}
    };
  },
  methods: {
    
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
