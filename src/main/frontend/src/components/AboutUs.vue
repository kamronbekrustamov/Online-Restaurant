<template>
  <h2 class="center">About Us</h2>
  <section class="container-fluid">
    <div class="row">
      <div class="col l6">
        <img
          src="@/assets/logo_big.png"
          alt="Logo"
          class="responsive-img materialbox"
        />
      </div>
      <div class="col l6">
        <div class="row two-back">
          <div class="col l2">
            <img
              src="@/assets/pro.png"
              alt="Portrait"
              class="responsive-img materialbox"
            />
          </div>
          <div class="row l3">
            <h5 class="indigo-text text-darken-4">Checked</h5>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Minima,
              corporis totam. Beatae nobis porro, impedit libero autem doloribus
              dolore modi ad
            </p>
          </div>
        </div>        
        <div class="row one-back">
          <div class="col l2">
            <img
              src="@/assets/offer.png"
              alt="Portrait"
              class="responsive-img materialbox"
            />
          </div>
          <div class="row l3">
            <h5 class="indigo-text text-darken-4">Offers</h5>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Minima,
              corporis totam. Beatae nobis porro, impedit libero autem doloribus
              dolore modi ad
            </p>
          </div>
        </div>        
        <div class="row two-back">
          <div class="col l2">
            <img
              src="@/assets/prideof.png"
              alt="Portrait"
              class="responsive-img materialbox"
            />
          </div>
          <div class="row l3">
            <h5 class="indigo-text text-darken-4">Pride</h5>
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Minima,
              corporis totam. Beatae nobis porro, impedit libero autem doloribus
              dolore modi ad
            </p>
          </div>
        </div>        
      </div>
    </div>
  </section>
</template>

<style scoped>
.one-back {
  position: relative;
  left: -50px;
}
.two-back {
  position: relative;
  left: -100px;
}

@media screen and (max-width: 992px) {
  .one-back {
    position: static;
    left: 0px;
  }
  .two-back {
    position: static;
    left: 0px;
  } 
}
</style>