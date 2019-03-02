 <%@tag pageEncoding="UTF-8" %>


  <header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 pt-1">
      </div>
      <div class="col-4 text-center">
        <h3 class="blog-header-logo text-dark" href="#">Gallery App</h3>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
        <!--<a class="btn btn-sm btn-outline-secondary" href="#">Upload image</a>-->
          <div class="upload-btn-wrapper">
              <form method="POST" action="uploadFile" enctype="multipart/form-data">
                  <input type="file"
                         accept="image/x-png,image/gif,image/jpeg">
                  <button class="btn">Upload image</button>
              </form>
          </div>
      </div>

<%--<div>
            <a href="welcome">Click here to See Welcome Message... </a>(to
            check Spring MVC Controller... @RequestMapping("/welcome"))
        </div>
        --%>

    </div>
  </header>