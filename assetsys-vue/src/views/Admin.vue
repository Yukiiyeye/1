<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav></admin-nav>
      </el-aside>
      <el-main>
        <el-carousel indicator-position="outside" style="max-width: 700px; max-height: 600px;">
          <el-carousel-item v-for="item in imageList" :key="item">
            <img :src="getImagePath(item)" alt="轮播图片" style="width: 100%; height: 100%; object-fit: cover;">
          </el-carousel-item>
        </el-carousel>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import AdminNav from '@/components/AdminNav.vue';
import image1 from '@/assets/img/image1.jpg';
import image2 from '@/assets/img/image2.jpg';
import image3 from '@/assets/img/image3.jpg';
import image4 from '@/assets/img/image4.jpg';

export default {
  components: {
    AdminNav,
  },
  data() {
    return {
      imageList: [image1, image2, image3, image4],
    };
  },
  methods: {
    getImagePath(item) {
      return item.default || item;
    },
  },
};
</script>

<style scoped>
/* Your existing styling for carousel items */
.el-carousel__item img {
  //width: 100%;
  //height: 100%;
  object-fit: cover;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
