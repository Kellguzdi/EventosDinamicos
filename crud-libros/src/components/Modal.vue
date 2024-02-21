<template>
  <b-modal
    id="chargeData"
    title="Cargando información"
    hide-footer
    centered
    hide-header-close
    no-close-on-backdrop
    no-close-on-esc
    :visible="isLoading"
  >
    <b-container fluid>
      <b-row>
        <b-col>
          <Spinner />
        </b-col>
      </b-row>
    </b-container>
  </b-modal>
</template>

<script>
import Vue from "vue";
import Spinner from "./Spinner.vue";

export default Vue.extend({
  components: { Spinner },
  props: {
    isLoading: {
      type: Boolean,
      required: true,
    },
  },
  data() {
    return {};
  },
});
</script>
