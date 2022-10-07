package rs.raf.demo.admincms;

import rs.raf.demo.entities.Category;
import rs.raf.demo.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cms/category")
public class AdminCMSCategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category create(Category category) {
        return this.categoryService.addCategory(category);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category edit(Category category) {
        return this.categoryService.editCategory(category);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.categoryService.deleteCategory(id);
    }

}
