package com.pixsterstudio.androidarckxml.utils

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.*
import com.google.common.collect.ImmutableList

class InAppSubscription constructor(
    var context: Activity
):PurchasesUpdatedListener {
    private var billingClient: BillingClient? = null
    var repeat = 1
    var productDataList = mutableListOf<QueryProductDetailsParams.Product>()

    var onPurchasedSuccessCallBack: (Purchase) -> Unit = {}
    var onPurchasedErrorCallBack: (Int) -> Unit = {}

    //------HERE INITIALIZE IN APP BILLING-------//
    fun initializeBillingClient(
        productList: List<QueryProductDetailsParams.Product>,
        onInitializeBillingCallBack: (Int) -> Unit
    ) {
        this.productDataList = productList as MutableList<QueryProductDetailsParams.Product>

        if (billingClient == null) {
            this.billingClient = BillingClient.newBuilder(context)
                .setListener(this)
                .enablePendingPurchases()
                .build()
        }

        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {

                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    onInitializeBillingCallBack(Constants.InApp.BILLING_CLIENT_STATUS_SUCCESS)
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                onInitializeBillingCallBack(Constants.InApp.BILLING_CLIENT_STATUS_FAIL)
            }
        })
    }


    fun checkPurchaseHistory( purchase: (String,Boolean) -> Unit) {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
        billingClient?.queryPurchasesAsync(params.build()) { billingResult, purchaseList ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.SERVICE_DISCONNECTED) {
                onPurchasedErrorCallBack(billingResult.responseCode)
                purchase("",true)
            }
            else
            {
                purchaseList.forEach {
                    //------Purchase update listener loop-----//
                    if (it?.purchaseState == Purchase.PurchaseState.PURCHASED) {
                        //------- This block call when product purchased successfully -------//
                        //---- Make server api call for store purchased product details ----//
                        if (!it.isAcknowledged) {
                            acknowledgePurchaseTwo(it)
                        } else if (it.isAcknowledged) {
                            purchase(it.purchaseToken,true)
                        } else {
                            //
                        }
                    }
                }
            }
        }
    }

    //------HERE LOAD PRODUCT LIST(After successfully initialize billing)----//
    fun loadProductList(callBack: (ArrayList<ProductDetails>) -> Unit): List<ProductDetails> {
        var skuDetailsList = mutableListOf<ProductDetails>()

        if (billingClient!!.isReady) {
            val params = QueryProductDetailsParams
                .newBuilder()
                .setProductList(productDataList)
                .build()

            billingClient!!.queryProductDetailsAsync(params) { billingResult, skuList ->
                // Process the result.
                Log.d(
                    "billing Result",
                    "Code : ${billingResult.responseCode}  message : ${billingResult.debugMessage}"
                )

                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && skuList.isNotEmpty()) {
                    //  Log.e("List",skuList.toString())
                    callBack(skuList as ArrayList<ProductDetails>)
                    skuDetailsList = skuList
                }
            }
        } else {
            //------call when billing client is not ready--------//
            Log.d("inapp", "Billing Client not ready")
        }
        return skuDetailsList
    }

    fun launchingBillingFlow(listProductDetailsParams: List<BillingFlowParams.ProductDetailsParams>) {
        Log.d("TAG", "launchingBillingFlow: :::")
        val billingFlowParams = BillingFlowParams
            .newBuilder()
            .setProductDetailsParamsList(listProductDetailsParams)
            .build()
        billingClient!!.launchBillingFlow(
            context,
            billingFlowParams
        )
    }


    private fun acknowledgePurchase(purchase: Purchase) {
        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient?.acknowledgePurchase(params) { billingResult ->
        }
    }

    private fun acknowledgePurchaseTwo(purchase: Purchase) {
        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient?.acknowledgePurchase(params) { billingResult ->
            val responseCode = billingResult.responseCode
            if (responseCode == BillingClient.BillingResponseCode.OK) {
                Log.d("Repeat", "acknowledgePurchase: $repeat")
                repeat++
                consumeProduct(purchase)
            }
        }
    }

    private fun consumeProduct(purchaseToken: Purchase?) {
        billingClient?.consumeAsync(
            ConsumeParams.newBuilder()
                .setPurchaseToken(purchaseToken?.purchaseToken!!)
                .build()
        ) { billing, purchaseToken ->
        }
    }

     fun productList(): ImmutableList<QueryProductDetailsParams.Product> {
        return ImmutableList.of(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.InApp.WEEKLY)
                .setProductType(
                    BillingClient.ProductType.SUBS
                )
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.InApp.MONTHLY)
                .setProductType(
                    BillingClient.ProductType.SUBS
                )
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.InApp.YEARLY)
                .setProductType(
                    BillingClient.ProductType.SUBS
                )
                .build()
        )
    }

    //------BELOW METHOD FOR GET PURCHASED PLAN DETAILS---------//
    fun getPurchasedProductsDetails(
        listPurchase: (List<Purchase>) -> Unit
    ) {
        if (billingClient!!.isReady) {
            val params = QueryPurchasesParams
                .newBuilder()
                .setProductType(BillingClient.ProductType.SUBS)
                .build()

            billingClient?.queryPurchasesAsync(params){result,list->
                listPurchase.invoke(list)
            }
        }
    }

    fun destroyMethod() {
       if(billingClient!=null){
           billingClient?.endConnection()
           billingClient = null
       }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
            val responseCode = billingResult.responseCode
            val debugMessage = billingResult.debugMessage
            Log.e("purchase Listener",purchases.toString())
            Log.e("billingResult Listener",billingResult.toString())
            //Log.e("Purchase",purchases.toString())
            when (responseCode) {
                BillingClient.BillingResponseCode.OK -> {
                    Log.d("Purchase", ":${purchases?.size} ")
                    purchases?.forEach {
                        //------Purchase update listener loop-----//
                        if (it.purchaseState == Purchase.PurchaseState.PURCHASED) {
                            //------- This block call when product purchased successfully -------//
                            //---- Make server api call for store purchased product details ----//
                            if (!it.isAcknowledged) {
                                Log.d("Product>>>>>>", ": ${it.orderId}")
                                acknowledgePurchase(it)
                                if (repeat==1){
                                    onPurchasedSuccessCallBack(it)
                                }
                                repeat++
                            } else {
                                //-----Call when get any other error-----//
                            }
                        }
                    }
                }
                BillingClient.BillingResponseCode.USER_CANCELED -> {
                    //------User cancel payment process--------//
                    onPurchasedErrorCallBack(Constants.InApp.PURCHASE_ERROR_USER_CANCELLED)
                }
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                    //------User already purchased product--------//
                    onPurchasedErrorCallBack(Constants.InApp.PURCHASE_ERROR_ITEM_ALREADY_OWN)
                }
                BillingClient.BillingResponseCode.DEVELOPER_ERROR -> {
                    onPurchasedErrorCallBack(Constants.InApp.PURCHASE_ERROR_DEVELOPER_ERROR)
                    //------Developer not configure properly on --------//
                    /**
                    Developer error means that Google Play
                    does not recognize the configuration. If you are just getting started,
                    make sure you have configured the application correctly in the Google Play Console. The SKU product ID must match and the APK you
                    are using must be signed with release keys.
                     */
                }
            }
    }

}