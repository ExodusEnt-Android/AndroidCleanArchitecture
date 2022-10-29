package com.example.presentation.databinding;
import com.example.presentation.R;
import com.example.presentation.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentTopNewsBindingImpl extends FragmentTopNewsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(4);
        sIncludes.setIncludes(0, 
            new String[] {"toolbar_title", "toolbar_back"},
            new int[] {1, 2},
            new int[] {com.example.presentation.R.layout.toolbar_title,
                com.example.presentation.R.layout.toolbar_back});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rv_top_news_list, 3);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentTopNewsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private FragmentTopNewsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.recyclerview.widget.RecyclerView) bindings[3]
            , (com.example.presentation.databinding.ToolbarTitleBinding) bindings[1]
            , (com.example.presentation.databinding.ToolbarBackBinding) bindings[2]
            );
        this.clParent.setTag(null);
        setContainedBinding(this.toolbar);
        setContainedBinding(this.toolbarBack);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        toolbar.invalidateAll();
        toolbarBack.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (toolbar.hasPendingBindings()) {
            return true;
        }
        if (toolbarBack.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        toolbar.setLifecycleOwner(lifecycleOwner);
        toolbarBack.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeToolbar((com.example.presentation.databinding.ToolbarTitleBinding) object, fieldId);
            case 1 :
                return onChangeToolbarBack((com.example.presentation.databinding.ToolbarBackBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeToolbar(com.example.presentation.databinding.ToolbarTitleBinding Toolbar, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeToolbarBack(com.example.presentation.databinding.ToolbarBackBinding ToolbarBack, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(toolbar);
        executeBindingsOn(toolbarBack);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): toolbar
        flag 1 (0x2L): toolbarBack
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}