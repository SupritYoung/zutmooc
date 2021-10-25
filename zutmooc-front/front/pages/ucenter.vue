<template>
  <!-- 内容区域 -->
  <div class="bg-fa of">
    <section class="container">
      <div class="u-body mt40">
        <menu class="col-3 fl uMenu">
          <header class="comm-title">
            <h2 class="fl tac">
              <span class="c-333">
                <a href="/ucenter" title>个人中心</a>
              </span>
            </h2>
          </header>

          <dl>
            <dd class="u-m-dd">
              <ul>
                <li>
                  <span>个人信息</span>
                  <ol>
                    <router-link to="/ucenter/order" tag="li" active-class="current" exact>
                      <a>我的订单</a>
                    </router-link>
                    <router-link to="/ucenter/collect" tag="li" active-class="current" exact>
                      <a>我的收藏</a>
                    </router-link>
                  </ol>
                </li>
              </ul>
            </dd>
          </dl>
        </menu>

        <nuxt-child/>

        <div class="clear"/>
      </div>
    </section>
  </div>
  <!-- /内容区域 -->
</template>

<script>
export default {}
</script>
