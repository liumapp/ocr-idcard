/**
 * @file index.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/13/18
 */
<template>
<div>
  <Row>
    <Col span="18" offset="3">
      <Card>
        <image-editor></image-editor>
      </Card>
    </Col>
  </Row>
</div>
</template>
<script>
import ImageEditor from '@/components/ImageEditor'
export default {
  name: 'index',
  components: {
    ImageEditor
  }
}
</script>
