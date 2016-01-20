/**
 * 
 */
package py.org.icarusdb.util;


/**
 * @author rgamarra
 *
 */
public abstract class NavigationRulez
{
    public String getModuleUri()
    {
        return AppHelper.getDomainUrl();
    }
    
    public String goRoot()
    {
        return NavigationRulezHelper.ROOT;
    }
    
    /**
     * for redirecting use
     * @return
     */
    public String goIndex()
    {
        return AppHelper.getDomainUrl() + NavigationRulezHelper.INDEX_JSF;
    }
    
    
}
