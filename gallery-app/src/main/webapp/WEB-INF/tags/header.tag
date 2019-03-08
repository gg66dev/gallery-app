 <%@tag pageEncoding="UTF-8" %>


  <header class="blog-header py-3">
    <div ng-controller="headerController"
            class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1">
      </div>
      <div class="col-4 text-center">
        <h3 class="blog-header-logo text-dark"><a href="/">Gallery App</a></h3>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
        <file-upload on-finish="uploadImage" />
      </div>
    </div>
  </header>