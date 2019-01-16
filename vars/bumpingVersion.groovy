def call(){
    def COOKBOOK_VERSION = sh(script: 'cat metadata.rb | grep ^version | awk '{print $2}' | sed "s|\'||g"')
    def NEW_COOOKBOOK_VERSION = sh(script: 'echo $COOKBOOK_VERSION | awk -F. -v OFS=. \'NF==1{print ++$NF}; NF>1{if(length($NF+1)>length($NF))$(NF-1)++; $NF=sprintf("%0*d", length($NF), ($NF+1)%(10^length($NF))); print}\'')
    sh """
        sed -i "s|version '${COOOKBOOK_VERSION}|version '${NEW_COOOKBOOK_VERSION}|g" metadata.rb
        git commit -am "Updating cookbook version to: '${NEW_COOOKBOOK_VERSION}'" &&\n git push origin HEAD:'${env.BRANCH_NAME}'
    """
}
