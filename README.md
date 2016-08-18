# PulltoRefresh
RecyclerView Pulltorefresh and loadmore
it still has some problems ，when your recyclerview LayoutManager is StaggeredGridLayoutManager
then you pull to refresh the data you will get empty view 
 这个项目还有问题，当你把recyclerview的LayoutManager设置成StaggeredGridLayoutManager的时候，下拉刷新会得到空白的view
 填充在最上面（解决办法是不再回调中再次设置adapter，直接notify的话就不会有问题）。这个应该是StaggeredGridLayoutManager的
 一个小bug
