
import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J>\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\nH\'\u00a8\u0006\f"}, d2 = {"LApiService;", "", "getTopHeadLines", "Lretrofit2/Call;", "Lcom/example/presentation/model/BaseDataModel;", "Lcom/example/presentation/model/Article;", "country", "", "category", "page", "", "pageSize", "presentation_debug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/v2/top-headlines")
    public abstract retrofit2.Call<com.example.presentation.model.BaseDataModel<com.example.presentation.model.Article>> getTopHeadLines(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "country")
    java.lang.String country, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Query(value = "category")
    java.lang.String category, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "pageSize")
    int pageSize);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}