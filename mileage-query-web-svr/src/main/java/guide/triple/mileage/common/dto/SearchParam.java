package guide.triple.mileage.common.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SearchParam {

    private final int pagePerSize;

    private final int page;

    private final Map<String, Object> searchKeyword;

    private SearchParam(Builder builder) {
        this.page = builder.page == 0 || builder.page < 0 ? 1 : builder.page;
        this.pagePerSize = builder.pagePerSize == 0 || builder.pagePerSize < 0 ? 10 : builder.pagePerSize;
        this.searchKeyword = Map.copyOf(builder.searchKeyword);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Builder() {}

        private int pagePerSize;

        private int page;

        private final Map<String, Object> searchKeyword = new HashMap<>();

        public Builder pagePerSize(int pagePerSize) {
            this.pagePerSize = pagePerSize;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder searchKeyword(String key, Object value) {
            this.searchKeyword.put(key, value);
            return this;
        }

        public Builder searchKeyword(Map.Entry<String, Object> entry) {
            this.searchKeyword.entrySet().add(entry);
            return this;
        }

        public Builder searchKeyword(Map<String, Object> searchKeyword) {
            this.searchKeyword.putAll(searchKeyword);
            return this;
        }

        public SearchParam build() {
            return new SearchParam(this);
        }
    }
}
