<template>
  <b-modal
    id="editBookModal"
    title="Editar Libro"
    no-close-on-backdrop
    hide-header-close
    no-close-on-esc
    ok-title="Editar"
    ok-variant="success"
    cancel-title="Cancelar"
    cancel-variant="danger"
    @ok="editBook"
    :ok-disabled="book.title.trim() === ''"
    @cancel="closeModal"
  >
    <b-container fluid>
      <form @submit.prevent="editBook">
        <b-form-group label="Título:" label-for="title">
          <b-form-input v-model="book.title" id="title" required />
        </b-form-group>
        <b-form-group label="Autor:" label-for="author">
          <b-form-select
            v-model="book.autor.id"
            :options="authors"
            value-field="id"
            text-field="name"
          />
        </b-form-group>
        <b-form-group label="Género:" label-for="category">
          <b-form-select
            v-model="book.categoria.id"
            :options="categories"
            value-field="id"
            text-field="nameCategory"
          />
        </b-form-group>
        <b-form-group label="Año de publicación:" label-for="issueDate">
          <b-form-input
            v-model="book.issueDate"
            id="issueDate"
            type="number"
            required
          />
        </b-form-group>
        <b-form-group label="Imagen:" label-for="image">
          <b-form-input v-model="book.image" id="image" required />
        </b-form-group>
      </form>
      <ModalSpinner :isLoading="isLoading" />
    </b-container>
  </b-modal>
</template>

<script>
import Vue from "vue";
import bookService from "../books/Book";
import authorsService from "../authors/Author";
import categoriesService from "../categories/Category";

export default Vue.extend({
  components: {
    ModalSpinner: () => import("../components/Modal.vue"),
  },
  props: {
    book: Object,
  },
  data() {
    return {
      authors: [],
      categories: [],
      isLoading: false,
    };
  },
  mounted() {
    this.closeModal();
    this.getAuthors();
    this.getCategories();
  },
  methods: {
    async editBook() {
      this.isLoading = true;
      try {
        const publicationDate = new Date(this.book.issueDate, 0, 1);
        this.book.issueDate = publicationDate;
        const response = await bookService.updateBook(this.book);

        if (!response.error) {
          this.isLoading = false;
          this.$emit("bookEdited");
          this.closeModal();
        }
      } catch (error) {
        console.log(error);
        this.isLoading = false;
      }
    },
    async getAuthors() {
      try {
        const data = await authorsService.getAuthors();
        this.authors = data.data;
      } catch (error) {
        console.log(error);
      }
    },
    async getCategories() {
      try {
        const data = await categoriesService.getCategories();
        this.categories = data.data;
      } catch (error) {
        console.log(error);
      }
    },
    closeModal() {
      this.$bvModal.hide("editBookModal");
    },
  },
});
</script>

<style></style>
