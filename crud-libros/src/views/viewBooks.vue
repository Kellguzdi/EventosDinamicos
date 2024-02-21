<template>
  <b-container fluid>
    <b-row>
      <b-col>
        <h1 style="text-align: center; margin-top: 10px">Books</h1>
      </b-col>
    </b-row>
    <hr />
    <b-row class="d-flex justify-content-center">
      <h3 style="margin-top: 20px">Agregar libro por drop</h3>
    </b-row>
    <b-row class="d-flex justify-content-center">
      <form>
        <b-form-group label="Título:" label-for="title">
          <b-form-input v-model="newBook.title" id="title" required />
        </b-form-group>
        <b-form-group label="Autor:" label-for="author">
          <b-form-select
            v-model="newBook.autor.id"
            :options="authors"
            value-field="id"
            text-field="name"
            required
          />
        </b-form-group>
        <b-form-group label="Género:" label-for="category">
          <b-form-select
            v-model="newBook.categoria.id"
            :options="categories"
            value-field="id"
            text-field="nameCategory"
            required
          />
        </b-form-group>
        <b-form-group label="Año de publicación:" label-for="issueDate">
          <b-form-input
            v-model="newBook.issueDate"
            id="issueDate"
            type="number"
            required
          />
        </b-form-group>
        <b-form-group label="Imagen:" label-for="image">
          <b-form-input v-model="newBook.image" id="image" required />
        </b-form-group>
      </form>
    </b-row>
    <hr />
    <b-row>
      <b-col class="d-flex justify-content-center">
        <b-button
          variant="info"
          @click="openAddBookModal()"
          style="margin: 10px"
        >
          Añadir libro
        </b-button>
      </b-col>
    </b-row>
    <ModalSpinner :isLoading="isLoading" />
    <b-row class="justify-content-center mt-3">
      <b-col cols="5" v-if="filter === 'title' || filter === 'author'">
        <b-form-group label="Buscar libro:" label-for="searchInput">
          <b-form-input
            v-model="textFilter"
            placeholder="Mil Noches"
            type="text"
            id="searchInput"
            @input="getBooksByTitleOrAuthor()"
          />
        </b-form-group>
      </b-col>
      <b-col cols="5" v-else-if="filter === 'category'">
        <b-form-group label="Buscar genero:" label-for="categorySelect">
          <b-form-select
            v-model="selectedCategory"
            :options="categories"
            value-field="nameCategory"
            text-field="nameCategory"
            id="categorySelect"
            @change="getBooksByCategory()"
          />
        </b-form-group>
      </b-col>
      <b-row v-else-if="filter === 'dateRange'">
        <b-col cols="4">
          <b-form-group label="Año inicial:" label-for="inicialYear">
            <b-form-input
              v-model="fechaInicio"
              type="number"
              placeholder="2021"
              id="inicialYear"
            />
          </b-form-group>
        </b-col>
        <b-col cols="4">
          <b-form-group label="Año final:" label-for="finalYear">
            <b-form-input
              v-model="fechaFinal"
              type="number"
              placeholder="2024"
              id="finalYear"
            />
          </b-form-group>
        </b-col>
        <b-col cols="4">
          <b-form-group label="Filtrar entre años:" label-for="buttonFilter">
            <b-button @click="getBooksByDateRange()">Buscar</b-button>
          </b-form-group>
        </b-col>
      </b-row>
      <b-col cols="5" v-else>
        <b-form-group label="Order por año de publicación:">
          <b-button @click="getBooksByDateDesc()"
            >Odernar descendentemente</b-button
          >
        </b-form-group>
      </b-col>
      <b-col cols="5">
        <b-form-group label="Realizar busqueda por:" label-for="searchSelect">
          <b-form-select
            v-model="filter"
            :options="options"
            id="searchSelect"
          />
        </b-form-group>
      </b-col>
    </b-row>
    <b-row class="justify-content-center" v-if="!isLoading">
      <b-card
        v-for="(book, index) in books"
        :key="index"
        :title="book.title"
        :img-src="book.image"
        img-alt="imagen"
        img-top
        style="max-width: 15rem; margin: 10px"
        class="mb-2 card"
        @mouseover="showButtons(index)"
        @mouseleave="hideButtons(index)"
        v-bind:style="{ animationDelay: `${index * 0.1}s` }"
      >
        <b-card-text>
          <b-row>
            <b-col>
              <b-card-text
                >Autor: {{ book.autor && book.autor.name }}</b-card-text
              >
              <b-card-text
                >Genero:
                {{ book.categoria && book.categoria.nameCategory }}</b-card-text
              >
              <b-card-text
                >Año de publicación:
                {{ formatDate(book.issueDate) }}</b-card-text
              >
            </b-col>
          </b-row>
        </b-card-text>
        <b-row v-if="showButtonsIndex === index" class="text-center">
          <b-col>
            <b-button variant="warning" @click="openEditBookModal(book)"
              ><font-awesome-icon icon="fa-solid fa-pen-to-square"
            /></b-button>
          </b-col>
          <b-col>
            <b-button variant="danger" @click="deleteBook(index)"
              ><font-awesome-icon icon="fa-solid fa-trash"
            /></b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-row>
    <AddBookModal @bookAdded="refreshBookList" />
    <EditBookModal @bookEdited="refreshBookList" :book="selectedBook" />
  </b-container>
</template>

<script>
import Vue from "vue";
import bookService from "../books/Book";
import categoriesService from "../categories/Category";

export default Vue.extend({
  components: {
    ModalSpinner: () => import("../components/Modal.vue"),
    AddBookModal: () => import("../components/AddBookModal.vue"),
    EditBookModal: () => import("../components/EditBookModal.vue"),
  },
  data() {
    return {
      books: [],
      isLoading: true,
      showButtonsIndex: null,
      selectedBook: null,
      filter: "title",
      options: [
        { value: "title", text: "Título" },
        { value: "author", text: "Autor" },
        { value: "category", text: "Género" },
        { value: "dateRange", text: "Rango de Fechas" },
        { value: "dateDesc", text: "Fechas Descendentes" },
      ],
      selectedCategory: null,
      fechaInicio: null,
      fechaFinal: null,
      categories: [],
      textFilter: "",
      newBook: {
        title: "",
        autor: {
          id: null,
        },
        categoria: {
          id: null,
        },
        issueDate: null,
        image: "",
      },
      authors: [],
      categories: [],
    };
  },
  mounted() {
    this.getBooks();
    this.getCategories();
  },
  methods: {
    async getBooks() {
      try {
        const data = await bookService.getBooks();
        this.books = data.data;
        this.isLoading = false;
      } catch (error) {
        console.log(error);
        this.isLoading = false;
      }
    },
    async getBooksByTitleOrAuthor() {
      if (this.filter === "title") {
        try {
          const data = await bookService.getBooksByTitle(this.textFilter);
          this.books = data;
        } catch (error) {
          console.log(error);
        }
      } else if (this.filter === "author") {
        try {
          const data = await bookService.getBooksByAuthor(this.textFilter);
          this.books = data;
        } catch (error) {
          console.log(error);
        }
      }
    },
    async getBooksByCategory() {
      try {
        const data = await bookService.getBooksByCategory(
          this.selectedCategory
        );
        this.books = data;
      } catch (error) {
        console.log(error);
      }
    },
    async getBooksByDateRange() {
      if (this.fechaInicio !== null && this.fechaFinal !== null) {
        try {
          const initialDate = this.fechaInicio.toString() + "-01-01";
          const finalDate = this.fechaFinal.toString() + "-12-31";
          const data = await bookService.getBooksByDateBetween(
            initialDate,
            finalDate
          );
          this.books = data;
        } catch (error) {
          console.log(error);
        }
      } else {
        console.log("los años no son validos");
      }
    },
    async getBooksByDateDesc() {
      try {
        const data = await bookService.getBooksByDateDesc();
        this.books = data;
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
    openAddBookModal() {
      this.$nextTick(() => {
        this.$bvModal.show("addBookModal");
      });
    },
    openEditBookModal(book) {
      this.selectedBook = book;
      this.selectedBook.issueDate = new Date(book.issueDate).getFullYear();

      this.$nextTick(() => {
        this.$bvModal.show("editBookModal");
      });
    },
    refreshBookList() {
      this.getBooks();
    },
    showButtons(index) {
      this.showButtonsIndex = index;
    },
    hideButtons(index) {
      this.showButtonsIndex = null;
    },
    deleteBook(index) {
      try {
        const book = this.books[index];
        bookService.deleteBook(book.id);
        this.books.splice(index, 1);
      } catch (error) {
        console.log(error);
      }
    },
    formatDate(date) {
      return new Date(date).getFullYear();
    },
  },
});
</script>

<style>
.card {
  animation: fade-in 0.5s ease forwards;
  opacity: 0;
}

@keyframes fade-in {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>