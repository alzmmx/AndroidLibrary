package com.mx.ui.statelayout

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.mx.ui.statelayout.simple.NetworkErrorStateView
import com.mx.ui.statelayout.simple.SearchResultEmptyStateView
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 *
 * @author Mx
 * @date 2023/03/08
 */
class StateLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mState = State.CONTENT

    private var stateViews = SparseArray<IStateView>(3)


    internal fun init(builder: Builder) {
        this.stateViews = builder.stateViews
        stateViews.get(mState.value).show(this)
    }

    fun showStateView(state: State) {
        if (state.value != mState.value) {
            val stateView = stateViews.get(state.value) ?: return
            val oldStateView = stateViews.get(mState.value) ?: return
            oldStateView.hide(this)
            stateView.show(this)
            mState = state
        }
    }

    fun getIStateView(state: State): IStateView? {
        return stateViews.get(state.value)
    }

    fun <T : IStateView> getStateView(state: State): T? {
        return stateViews.get(state.value) as? T
    }

    fun getCurrentStateView() = getIStateView(mState)

    fun showContent() {
        showStateView(State.CONTENT)
    }

    fun showEmpty() {
        showStateView(State.EMPTY)
    }

    fun showLoading() {
        showStateView(State.LOADING)
    }

    fun addStateView(stateView: IStateView) {
        this.stateViews.append(stateView.getState().value, stateView)
    }

    fun removeStateView(state: State) {
        this.stateViews.remove(state.value)
    }


    companion object {
        @JvmStatic
        fun create(context: Context) = StateLayout(context)

        fun searchEmpty(target: View): StateLayout? {
            return Builder(target)
                .addStateView(SearchResultEmptyStateView())
                .build()
        }

        fun default(target: View, retry: (() -> Unit)? = null): StateLayout? {
            return Builder(target)
                .addStateView(DataEmptyStateView())
                .addStateView(NetworkErrorStateView(retry))
                .build()
        }
    }

    class Builder(val target: View) {
        private val mContext = target.context
        private val parent = target.parent as? ViewGroup
        private lateinit var stateLayout: StateLayout
        internal val stateViews = SparseArray<IStateView>(3)

        fun addStateView(stateView: IStateView): Builder {
            stateViews.put(stateView.getState().value, stateView)
            return this
        }

        fun build(): StateLayout? {
            if (parent != null) {
                stateLayout = create(mContext)
                replaceTargetView(parent.indexOfChild(target))
                addStateView(ContentStateView(target))
                stateLayout.init(this)
                return stateLayout
            }
            return null
        }

        private fun replaceTargetView(index: Int) {
            val params = target.layoutParams
            parent?.removeView(target)
            if (parent is SmartRefreshLayout){
                target.layoutParams = SmartRefreshLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                parent.addView(stateLayout, index, params)
                parent.requestLayout()
            }else {
                target.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                parent?.addView(stateLayout, index, params)
            }
        }

    }


}